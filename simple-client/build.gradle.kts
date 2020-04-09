plugins {
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")
    id("codes.pedromanoel.pact-source-set")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.+")

    pactImplementation("org.junit.jupiter:junit-jupiter:5.6.+")
    pactImplementation("com.konghq:unirest-java:3.7.+")
    pactImplementation("au.com.dius:pact-jvm-consumer-junit5:4.0.+")
    pactImplementation("au.com.dius:pact-jvm-consumer-java8:4.0.+")
}

pact {
    publish {
        pactDirectory = "${project.buildDir}/pacts"
        pactBrokerUrl = "http://localhost:9292"
    }
}