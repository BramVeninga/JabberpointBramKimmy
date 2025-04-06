plugins {
    id("java")
    checkstyle
    id("org.sonarqube") version "6.0.1.5171"
//    id("jacoco")
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
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-inline:4.8.1")
}

tasks.test {
    useJUnitPlatform()
}

sonar {
    properties {
        property("sonar.projectKey", "BramVeninga_JabberpointBramKimmy")
        property("sonar.organization", "bramveninga")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

//jacoco {
//    toolVersion = "0.8.8"
//}
//
//tasks.test {
//    useJUnitPlatform()
//    finalizedBy(tasks.jacocoTestReport) // Zorgt ervoor dat het rapport wordt gegenereerd na tests
//}
//
//tasks.jacocoTestReport {
//    dependsOn(tasks.test) // Zorg ervoor dat tests eerst draaien
//    reports {
//        xml.required.set(true)  // Belangrijk voor SonarCloud!
//        html.required.set(true) // Handig voor lokale debugging
//    }
//}

sourceSets {
    test {
        java.setSrcDirs(listOf("src/test/java"))
    }
}

