/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.enforce.sonarqube.apex.parser.grammar.trigger;

import org.fundacionjala.enforce.sonarqube.apex.parser.ApexRuleTest;
import org.junit.Before;
import org.junit.Test;

import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.FOR_STATEMENT;
import static org.sonar.sslr.tests.Assertions.assertThat;

public class ApexGrammarForEachLoopTest extends ApexRuleTest {

    @Before
    public void setUp() {
        setRootRule(FOR_STATEMENT);
    }

    @Test
    public void testValidForEachLoop() {
        assertThat(parser)
                .matches("for (Account a : Trigger.New) {}");
    }

    @Test
    public void testInvalidForEachLoop() {
        assertThat(parser)
                .notMatches("integer variable;")
                .notMatches("(integer variable: anotherVariable {}")
                .notMatches("integer variable anotherVariable) {}")
                .notMatches("somethingelse;");
    }
}
