/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package org.fundacionjala.enforce.sonarqube.apex.checks.checkstyle;

import java.io.File;

import org.fundacionjala.enforce.sonarqube.apex.ApexAstScanner;
import org.junit.Test;
import org.sonar.squidbridge.SquidAstVisitor;
import org.sonar.squidbridge.api.SourceFile;
import org.sonar.squidbridge.checks.CheckMessagesVerifier;

public class CheckStyleTest {

    private static final String FIRST_EXPRESSION_ERROR_MESSAGE = "Styling issue[Operator syntax: Integer] Sample Fix: Integer";
    private static final String SECOND_EXPRESSION_ERROR_MESSAGE = "Styling issue[Whitespace near '{'] Sample Fix: ) {";
    
    private SourceFile sourceFile;
    
    @Test
    public void testCheckstyleRules() {
        String filePath = "src/test/resources/checks/checkstyletest.cls";
        scanFilesWithCheck(filePath, new SfCheckStyle());

        CheckMessagesVerifier a = CheckMessagesVerifier.verify(sourceFile.getCheckMessages());
        CheckMessagesVerifier.verify(sourceFile.getCheckMessages())
                .next().atLine(3).withMessage(FIRST_EXPRESSION_ERROR_MESSAGE)
                .next().atLine(3).withMessage(SECOND_EXPRESSION_ERROR_MESSAGE);
    }
    
   
    /**
     * Scan files given a file path and the respective check to scan file
     * @param filePath the path of the file to be scanned.
     * @param check visitor.
     */
    private void scanFilesWithCheck(String filePath, SquidAstVisitor check) {
        File file = new File(filePath);
        sourceFile = ApexAstScanner.scanFile(file, check);
    }

}
