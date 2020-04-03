plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.71"
    id("au.com.dius.pact") version "4.0.8"
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("au.com.dius:pact-jvm-consumer-junit5:4.0.8")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}

pact {
    publish {
        pactDirectory = "${project.buildDir}/pacts"
        pactBrokerUrl = "http://localhost:9292"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}