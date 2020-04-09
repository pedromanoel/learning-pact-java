package codes.pedromanoel.pact.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.withGroovyBuilder

internal class IdeaTestSourceSetPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.register<Task>("fixIdeaModules") {
                group = "idea"
                description = "Mark the $SOURCE_SET_NAME as a test source root and test resources"
                markAsTestSourceRoot(SOURCE_SET_NAME)
                markAsTestResource(SOURCE_SET_NAME)
            }
        }
    }

    private fun Project.markAsTestSourceRoot(sourceSetName: String) {
        replaceTextInModuleConfigurationFile(
                moduleName = sourceSetName,
                token = "isTestSource=\"false\"",
                value = "isTestSource=\"true\"")
    }

    private fun Project.markAsTestResource(sourceSetName: String) {
        replaceTextInModuleConfigurationFile(
                moduleName = sourceSetName,
                token = "java-resource",
                value = "java-test-resource")
    }

    private fun Project.replaceTextInModuleConfigurationFile(
            moduleName: String,
            token: String,
            value: String
    ) {
        val modulesDir = "${rootProject.rootDir.path}/.idea/modules/${name}"
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

}