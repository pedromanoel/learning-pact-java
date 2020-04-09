import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")

    application
}

sourceSets {
    create("pact") {
        compileClasspath += sourceSets["main"].output
        runtimeClasspath += sourceSets["main"].output
    }
}

tasks.register<Test>("pactTest") {
    description = "Run pact tests against the test broker"
    group = "verification"

    testClassesDirs = sourceSets["pact"].output.classesDirs
    classpath = sourceSets["pact"].runtimeClasspath

    systemProperty("pact.provider.version", version)
    systemProperty("pact.verifier.publishResults", true)
}

tasks.register<Task>("fixIdeaModules") {
    group = "idea"
    sourceSets["pact"].markAsTestSourceRoot()
    sourceSets["pact"].markAsTestResource()
}

tasks.check {
    dependsOn("pactTest")
}

application {
    mainClassName = "codes.pedromanoel.pact.customer.MainKt"
}

configurations {
    named("pactRuntimeOnly") { extendsFrom(runtimeOnly.get()) }
    named("pactImplementation") { extendsFrom(implementation.get()) }
}

configurations.named("pactRuntimeOnly") {
    extendsFrom(configurations.runtimeOnly.get())
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

fun DependencyHandler.pactImplementation(dependencyNotation: Any): Dependency? =
        add("pactImplementation", dependencyNotation)

fun SourceSet.markAsTestSourceRoot() =
        replaceInModuleFile(name, "isTestSource=\"false\"", "isTestSource=\"true\"")

fun SourceSet.markAsTestResource() =
        replaceInModuleFile(name, "java-resource", "java-test-resource")

fun replaceInModuleFile(
        moduleName: String,
        token: String,
        value: String
) {
    val modulesDir = "${rootProject.rootDir.path}/.idea/modules/${project.name}"
    val moduleFileMatch = "*.$moduleName.iml"

    ant.withGroovyBuilder {
        "replace"(
                "dir" to modulesDir,
                "includes" to moduleFileMatch,
                "token" to token,
                "value" to value
        )
    }
}
