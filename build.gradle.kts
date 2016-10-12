buildscript {

    repositories {
        gradleScriptKotlin()
    }

    dependencies {
        classpath(kotlinModule("gradle-plugin"))
    }
}

apply {
    plugin("kotlin")
    plugin<ApplicationPlugin>()
}

repositories {
    gradleScriptKotlin()
    jcenter()
}

dependencies {
    compile(kotlinModule("stdlib"))
    compile("org.apache.commons:commons-lang3:3.4")
    compile("commons-io:commons-io:2.5")
    testCompile("junit:junit:4.12")
}
