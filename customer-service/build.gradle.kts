import au.com.dius.pact.provider.ConsumerInfo
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.*
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

plugins {
    id("com.github.johnrengelman.shadow")
    id("org.jetbrains.kotlin.jvm")
    id("au.com.dius.pact")

    application
}

val startApp by tasks.registering {
    description = "Executa a aplicação em background"
    doLast {
        println("Start App")
        val jarFile = "${project.name}-all.jar"
        ProcessBuilder()
                .directory(project.properties["libsDir"] as File)
                .command("java", "-jar", jarFile)
                .start()
                .pid()
                .run(Long::toString)
                .also { pid ->
                    File(".pid").writeText(pid)
                }

        TimeUnit.SECONDS.sleep(1)
    }
}

val stopApp by tasks.registering {
    description = "Para a execução em background da aplicação"
    doLast {
        println("Stop App")
        File(".pid")
                .readLines()
                .firstOrNull()
                ?.toLongOrNull()
                ?.let(ProcessHandle::of)
                ?.ifPresent { process ->
                    process.destroy()
                }
    }
}

pact {
    serviceProviders {
        create("CustomerService") {
            startProviderTask = "startApp"
            terminateProviderTask = "stopApp"
            port = 7000
            hasPactsFromPactBroker("http://localhost:9292", closureOf<ConsumerInfo> { })
        }
    }
}

sourceSets {
    create("pact") {
        withConvention(KotlinSourceSet::class) {
            compileClasspath += sourceSets["main"].output
            runtimeClasspath += sourceSets["main"].output
        }
    }
}

// TODO make intellij identify this sourceSet as test code
tasks.register<Test>("pactTest") {
    useJUnitPlatform()

    description = "Run pact tests"
    group = "verification"

    testClassesDirs = sourceSets["pact"].output.classesDirs
    classpath = sourceSets["pact"].runtimeClasspath
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED)
        exceptionFormat = FULL
    }
}

application {
    mainClassName = "codes.pedromanoel.pact.customer.MainKt"
}

val pactImplementation by configurations.getting {
    extendsFrom(configurations.implementation.get())
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