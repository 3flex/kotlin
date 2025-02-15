/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir.diagnostic;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link GenerateNewCompilerTests.kt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter")
@TestDataPath("$PROJECT_ROOT")
public class DiagnosticTraversalCounterTestGenerated extends AbstractDiagnosticTraversalCounterTest {
    @Test
    public void testAllFilesPresentInDiagnosticTraversalCounter() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("constructor.kt")
    public void testConstructor() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/constructor.kt");
    }

    @Test
    @TestMetadata("declarationsInPropertyInit.kt")
    public void testDeclarationsInPropertyInit() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/declarationsInPropertyInit.kt");
    }

    @Test
    @TestMetadata("enumClass.kt")
    public void testEnumClass() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/enumClass.kt");
    }

    @Test
    @TestMetadata("enumClassWithBody.kt")
    public void testEnumClassWithBody() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/enumClassWithBody.kt");
    }

    @Test
    @TestMetadata("functionalType.kt")
    public void testFunctionalType() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/functionalType.kt");
    }

    @Test
    @TestMetadata("initBlock.kt")
    public void testInitBlock() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/initBlock.kt");
    }

    @Test
    @TestMetadata("lambda.kt")
    public void testLambda() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/lambda.kt");
    }

    @Test
    @TestMetadata("localDeclarationsInAccessor.kt")
    public void testLocalDeclarationsInAccessor() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/localDeclarationsInAccessor.kt");
    }

    @Test
    @TestMetadata("localFunctionWithImplicitType.kt")
    public void testLocalFunctionWithImplicitType() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/localFunctionWithImplicitType.kt");
    }

    @Test
    @TestMetadata("localUnitFunction.kt")
    public void testLocalUnitFunction() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/localUnitFunction.kt");
    }

    @Test
    @TestMetadata("memberFunctions.kt")
    public void testMemberFunctions() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/memberFunctions.kt");
    }

    @Test
    @TestMetadata("memberProperties.kt")
    public void testMemberProperties() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/memberProperties.kt");
    }

    @Test
    @TestMetadata("memberTypeAlias.kt")
    public void testMemberTypeAlias() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/memberTypeAlias.kt");
    }

    @Test
    @TestMetadata("multipleTopLevelClasses.kt")
    public void testMultipleTopLevelClasses() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/multipleTopLevelClasses.kt");
    }

    @Test
    @TestMetadata("multipleTopLevelFunctionsWithImplicitTypes.kt")
    public void testMultipleTopLevelFunctionsWithImplicitTypes() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/multipleTopLevelFunctionsWithImplicitTypes.kt");
    }

    @Test
    @TestMetadata("multipleTopLevelUnitFunctions.kt")
    public void testMultipleTopLevelUnitFunctions() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/multipleTopLevelUnitFunctions.kt");
    }

    @Test
    @TestMetadata("nestedClases.kt")
    public void testNestedClases() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/nestedClases.kt");
    }

    @Test
    @TestMetadata("nestedClasesWithFun.kt")
    public void testNestedClasesWithFun() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/nestedClasesWithFun.kt");
    }

    @Test
    @TestMetadata("propertyAccessors.kt")
    public void testPropertyAccessors() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/propertyAccessors.kt");
    }

    @Test
    @TestMetadata("propertyWithGetterAndSetter.kt")
    public void testPropertyWithGetterAndSetter() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/propertyWithGetterAndSetter.kt");
    }

    @Test
    @TestMetadata("secondaryConstructor.kt")
    public void testSecondaryConstructor() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/secondaryConstructor.kt");
    }

    @Test
    @TestMetadata("typeAlias.kt")
    public void testTypeAlias() throws Exception {
        runTest("analysis/low-level-api-fir/testdata/diagnosticTraversalCounter/typeAlias.kt");
    }
}
