package codes.pedromanoel.pact.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register

internal class TestTaskPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            val pactSourceSet = sourceSets[SOURCE_SET_NAME]

            tasks.register<Test>("${SOURCE_SET_NAME}Test") {
                group = "verification"

                testClassesDirs = pactSourceSet.output.classesDirs
                classpath = pactSourceSet.runtimeClasspath
            }

            tasks.named("check") {
                dependsOn("${SOURCE_SET_NAME}Test")
            }
        }
    }
}