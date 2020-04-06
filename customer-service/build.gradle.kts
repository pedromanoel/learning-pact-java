import au.com.dius.pact.provider.ConsumerInfo

plugins {
    id("com.github.johnrengelman.shadow")
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

tasks.test {
    useJUnitPlatform()
}

application {
    mainClassName = "codes.pedromanoel.pact.customerservice.MainKt"
}