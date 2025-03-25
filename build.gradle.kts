plugins {
    id("java")
    checkstyle
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

checkstyle {
    toolVersion = "10.3"
    configFile = file("${rootDir}/config/checkstyle/checkstyle.xml")
}

tasks.withType<Checkstyle>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
    files(configurations.checkstyle.get().files)

    doFirst {
        println("Checkstyle classpath: ${configurations.checkstyle.get().files}")
    }
}

tasks.register<Checkstyle>("logCheckstyleConfig") {
    doFirst {
        println("Using Checkstyle config file: ${file("${rootDir}/config/checkstyle/checkstyle.xml").absolutePath}")
    }
}


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    checkstyle("com.puppycrawl.tools:checkstyle:10.3")
}

tasks.test {
    useJUnitPlatform()
}