plugins {
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")

    application
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("io.javalin:javalin:3.8.0")

    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

application {
    mainClassName = "codes.pedromanoel.AppKt"
}
