/*
 * Copyright (c) Fundacion Jala. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */
package org.fundacionjala.enforce.sonarqube.apex.checks;

import java.util.List;

import org.fundacionjala.enforce.sonarqube.apex.checks.soql.SoqlLimitCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.AssertLiteralBooleanCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.AssertMessageCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.SeeAllDataTestCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.TestClassCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.TestMethodInTestClassCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.testrelated.TestMethodsParametersCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.trigger.TriggerHardcodingIdsCheckInVariables;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.ClassNameCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.DeprecatedMethodCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.DmlInConstructorCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.DmlInForCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.DmlInWhileCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.EmptyCatchCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.LineLengthCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.MethodNameCheck;
import org.fundacionjala.enforce.sonarqube.apex.checks.unofficial.TestMethodCheck;

import com.google.common.collect.ImmutableList;

/**
 * Builds a list of custom checks.
 */
public class CheckList {

    /**
     * Stores the sonarqube profile name.
     */
    public static final String SONAR_WAY_PROFILE = "Sonar way";

    /**
     * Stores the sonarqube repository name.
     */
    public static final String REPOSITORY_NAME = "SonarQube";

    /**
     * Stores the Apex's repository key.
     */
    public static final String REPOSITORY_KEY = "apex";

    /**
     * Default constructor.
     */
    private CheckList() {
    }

    /**
     * Builds and returns the custom checks to create Apex's rules.
     *
     * @return the list of the checks.
     */
    public static List<Class> getChecks() {
        return ImmutableList.<Class>of(
                ErrorRecoveryCheck.class,
                ClassNameCheck.class,
                DeprecatedMethodCheck.class,
                DmlInConstructorCheck.class,
                DmlInForCheck.class,
                DmlInWhileCheck.class,
                LineLengthCheck.class,
                MethodNameCheck.class,
                TestMethodCheck.class,
                AsyncMethodsCheck.class,
                HardcodingIdsCheckInVariables.class,
                TriggerHardcodingIdsCheckInVariables.class,
                HardcodingIdsInMethodsAndConstructorsCheck.class,
                TestClassCheck.class,
                TestMethodInTestClassCheck.class,
                TestAssertionsAndTestMethodKeywordCheck.class,
                SeeAllDataTestCheck.class,
                AssertLiteralBooleanCheck.class,
                TestMethodsParametersCheck.class,
                AssertMessageCheck.class,
                SoqlLimitCheck.class,
                EmptyCatchCheck.class);
    }
}
