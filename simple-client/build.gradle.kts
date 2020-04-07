plugins {
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("au.com.dius:pact-jvm-consumer-junit5:4.0.8")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("com.konghq:unirest-java:3.7.00")
}

pact {
    publish {
        pactDirectory = "${project.buildDir}/pacts"
        pactBrokerUrl = "http://localhost:9292"
    }
}

tasks.test {
    systemProperty("pact.provider.version", version)
    systemProperty("pact.verifier.publishResults", true)
    useJUnitPlatform()
}