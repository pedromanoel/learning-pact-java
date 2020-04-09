import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

plugins {
    `kotlin-dsl`
}

apply<KotlinDslPlugin>()

repositories {
    jcenter()
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events = setOf(PASSED, SKIPPED, FAILED)
        exceptionFormat = FULL
    }
}

dependencies {
    implementation(gradleApi())
    implementation(localGroovy())

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}