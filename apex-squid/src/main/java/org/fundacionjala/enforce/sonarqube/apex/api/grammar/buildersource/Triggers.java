package org.fundacionjala.enforce.sonarqube.apex.api.grammar.buildersource;

import static org.fundacionjala.enforce.sonarqube.apex.api.ApexPunctuator.COLON;
import static org.fundacionjala.enforce.sonarqube.apex.api.ApexPunctuator.LPAREN;
import static org.fundacionjala.enforce.sonarqube.apex.api.ApexPunctuator.RPAREN;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.ALLOWED_KEYWORDS_AS_IDENTIFIER;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.TRIGGER_FOR_EACH_LOOP;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.STATEMENT;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.TRIGGER_OBJECT_DECLARATION;
import static org.fundacionjala.enforce.sonarqube.apex.api.grammar.ApexGrammarRuleKey.TYPE;

import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

public class Triggers {

	public static void create(LexerfulGrammarBuilder grammarBuilder) {
		forEachLoop(grammarBuilder);
	}
	
    /**
     * Defines the for each loop rule.
     *
     * @param grammarBuilder ApexGrammarBuilder parameter.
     */
    private static void forEachLoop(LexerfulGrammarBuilder grammarBuilder) {
        grammarBuilder.rule(TRIGGER_FOR_EACH_LOOP).is(LPAREN,
                TYPE,
                ALLOWED_KEYWORDS_AS_IDENTIFIER,
                COLON,
                TRIGGER_OBJECT_DECLARATION,
                RPAREN,
                STATEMENT
        );
    }

}
