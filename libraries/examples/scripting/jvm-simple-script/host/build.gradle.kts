
plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":examples:scripting-jvm-simple-script"))
    api(project(":kotlin-scripting-jvm-host-unshaded"))
    api(project(":kotlin-script-util"))
    testRuntimeOnly(project(":kotlin-compiler"))
    testRuntimeOnly(project(":kotlin-scripting-compiler"))
    testApi(commonDependency("junit"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}
