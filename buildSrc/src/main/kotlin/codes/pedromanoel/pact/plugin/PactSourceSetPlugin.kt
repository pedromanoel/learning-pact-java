package codes.pedromanoel.pact.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.apply

fun DependencyHandler.pactImplementation(dependencyNotation: Any): Dependency? =
        add("pactImplementation", dependencyNotation)

class PactSourceSetPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            apply<SourceSetPlugin>()
            apply<TestTaskPlugin>()
            apply<IdeaTestSourceSetPlugin>()
        }
    }
}
