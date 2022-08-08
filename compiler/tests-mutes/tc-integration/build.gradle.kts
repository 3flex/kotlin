plugins {
    kotlin("jvm")
    id("jps-compatible")
    application
}

dependencies {
    api(kotlinStdlib())
    implementation(project(":compiler:tests-mutes"))
    implementation("khttp:khttp:1.0.0") {
        exclude("org.jetbrains.kotlin")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.0") {
        exclude("org.jetbrains.kotlin")
    }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

val mutesPackageName = "org.jetbrains.kotlin.test.mutes"

application {
    mainClass.set("$mutesPackageName.MutedTestsSyncKt")
    applicationDefaultJvmArgs = rootProject.properties.filterKeys { it.startsWith(mutesPackageName) }.map { (k, v) -> "-D$k=$v" }
}
