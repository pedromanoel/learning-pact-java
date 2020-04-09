package codes.pedromanoel.pact.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.invoke

internal class SourceSetPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            sourceSets {
                create(SOURCE_SET_NAME) {
                    compileClasspath += sourceSets["main"].output
                    runtimeClasspath += sourceSets["main"].output
                }
            }
            configurations {
                named("${SOURCE_SET_NAME}RuntimeOnly") { extendsFrom(configurations["runtimeOnly"]) }
                named("${SOURCE_SET_NAME}Implementation") { extendsFrom(configurations["implementation"]) }
            }
        }
    }
}