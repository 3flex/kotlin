import tasks.WriteCopyrightToFile

plugins {
    kotlin("jvm")
    id("jps-compatible")
    application
}

val runtimeOnly by configurations
val compileOnly by configurations
runtimeOnly.extendsFrom(compileOnly)

dependencies {
    implementation(project(":generators"))
    implementation(project(":core:compiler.common"))
    implementation("com.squareup:kotlinpoet:1.11.0") {
        exclude("org.jetbrains.kotlin")
    }

    compileOnly(intellijCore())
    compileOnly(commonDependency("org.jetbrains.intellij.deps:trove4j"))

    runtimeOnly(commonDependency("org.jetbrains.intellij.deps:jdom"))
}

val writeCopyright by task<WriteCopyrightToFile> {
    outputFile.set(file("$buildDir/copyright/notice.txt"))
    commented.set(true)
}

application {
    mainClassName = "org.jetbrains.kotlin.ir.generator.MainKt"
}

val processResources by tasks
processResources.dependsOn(writeCopyright)

sourceSets {
    "main" {
        projectDefault()
        resources.srcDir("$buildDir/copyright")
    }
    "test" {}
}
