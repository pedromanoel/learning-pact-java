import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    id("com.github.johnrengelman.shadow") version "5.2.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.3.71" apply false
    id("au.com.dius.pact") version "4.0.8" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            events = setOf(FAILED)
            exceptionFormat = FULL
        }
    }
}