/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.enforce.sonarqube.apex.checks.testrelated;

import java.util.List;

import org.fundacionjala.enforce.sonarqube.apex.api.ApexKeyword;
import org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey;
import org.fundacionjala.enforce.sonarqube.apex.checks.ChecksBundle;
import org.sonar.check.Rule;
import org.sonar.squidbridge.checks.SquidCheck;

import com.sonar.sslr.api.AstNode;
import com.sonar.sslr.api.Grammar;
import org.fundacionjala.enforce.sonarqube.apex.checks.ChecksLogger;

/**
 * Verifies that only the proper classes are tagged as tests.
 */
@Rule(key = TestClassCheck.CHECK_KEY)
public class TestClassCheck extends SquidCheck<Grammar> {

    /**
     * It is the code of the rule for the plugin.
     */
    public static final String CHECK_KEY = "A1021";

    /**
     * Identifier of the annotation.
     */
    public static final String IS_TEST = "ISTEST";

    /**
     * Constant used to see if the name of the class has the word test in it.
     */
    private final String TEST = "TEST";

    private final String ANNOTATION_MESSAGE = ChecksBundle.getStringFromBundle("TestClassCheckAnnotationMessage");

    private final String NAME_MESSAGE = ChecksBundle.getStringFromBundle("TestClassCheckNameMessage");

    /**
     * The variables are initialized and subscribe the base rule.
     */
    @Override
    public void init() {
        subscribeTo(ApexGrammarRuleKey.CLASS_OR_INTERFACE_DECLARATION, ApexGrammarRuleKey.ENUM_DECLARATION);
    }

    /**
     * It is responsible for verifying whether the rule is met in the rule base.
     * In the event that the rule is not correct, create message error.
     *
     * @param astNode It is the node that stores all the rules.
     */
    @Override
    public void visitNode(AstNode astNode) {
        try {
            AstNode identifier = astNode.getFirstDescendant(ApexGrammarRuleKey.ALLOWED_KEYWORDS_AS_IDENTIFIER,
                    ApexGrammarRuleKey.SPECIAL_KEYWORDS_AS_IDENTIFIER);
            if (hasTestAnnotation(astNode)) {
                if (astNode.is(ApexGrammarRuleKey.ENUM_DECLARATION)
                        || astNode.getFirstDescendant(ApexGrammarRuleKey.TYPE_CLASS).hasDirectChildren(ApexKeyword.INTERFACE)) {
                    getContext().createLineViolation(this,
                            ANNOTATION_MESSAGE,
                            astNode, identifier.getTokenOriginalValue());
                }
            } else if (identifier.getTokenValue().contains(TEST)) {
                getContext().createLineViolation(this,
                        NAME_MESSAGE,
                        astNode, identifier.getTokenOriginalValue());
            }
        } catch (Exception e) {
            ChecksLogger.logCheckError(this.toString(), "visitNode", e.toString());
        }
    }

    /**
     * Determines whether or not a declarations has the @isTest annotation
     * amongst its modifiers.
     *
     * @param astNode the declaration to be checked.
     * @return true if the @isTest annotation is amongst the modifiers.
     */
    public static boolean hasTestAnnotation(AstNode astNode) {
        AstNode modifiers = astNode.getParent().getFirstDescendant(ApexGrammarRuleKey.MODIFIERS);
        if (modifiers.hasDescendant(ApexGrammarRuleKey.ANNOTATION)) {
            List<AstNode> annotations = modifiers.getDescendants(ApexGrammarRuleKey.ANNOTATION);
            for (AstNode annotation : annotations) {
                String annotationValue = annotation.getFirstDescendant(ApexGrammarRuleKey.NAME).getTokenValue();
                if (annotationValue.equals(IS_TEST)) {
                    return true;
                }
            }
        }
        return false;
    }
}
