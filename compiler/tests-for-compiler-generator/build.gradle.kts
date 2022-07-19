plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    testImplementation(kotlinStdlib())

    testApiJUnit5()
    testImplementation(projectTests(":compiler:tests-common"))
    testImplementation(projectTests(":compiler:test-infrastructure"))
    testImplementation(projectTests(":compiler:tests-common-new"))
    testImplementation(projectTests(":compiler"))
    testImplementation(projectTests(":compiler:fir:raw-fir:psi2fir"))
    testImplementation(projectTests(":compiler:fir:raw-fir:light-tree2fir"))
    testImplementation(projectTests(":compiler:fir:fir2ir"))
    testImplementation(projectTests(":compiler:fir:analysis-tests:legacy-fir-tests"))
    testImplementation(projectTests(":compiler:visualizer"))
    testImplementation(projectTests(":js:js.tests"))
    testImplementation(projectTests(":generators:test-generator"))
    // testCompileOnly(project(":kotlin-reflect-api"))
    // testRuntimeOnly(commonDependency("org.jetbrains.kotlin:kotlin-reflect")) { isTransitive = false }
}

sourceSets {
    "main" {}
    "test" { projectDefault() }
}

val generateTests by generator("org.jetbrains.kotlin.test.generators.GenerateCompilerTestsKt") {
    dependsOn(":compiler:generateTestData")
}

testsJar()
