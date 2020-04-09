plugins {
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")
    id("codes.pedromanoel.pact-source-set")

    application
}

tasks.named<Test>("pactTest") {
    description = "Run pact tests against the test broker"

    systemProperty("pact.provider.version", version)
    systemProperty("pact.verifier.publishResults", true)
}

application {
    mainClassName = "codes.pedromanoel.pact.customer.MainKt"
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.javalin:javalin:3.8.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.11.0.rc1")

    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.konghq:unirest-java:3.7.00")
    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")

    pactImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    pactImplementation("au.com.dius:pact-jvm-provider-junit5:4.0.8")
}

