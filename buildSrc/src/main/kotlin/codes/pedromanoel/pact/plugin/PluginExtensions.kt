package codes.pedromanoel.pact.plugin

import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.kotlin.dsl.getByType

internal const val SOURCE_SET_NAME = "pact"

internal val Project.sourceSets : SourceSetContainer
    get() = extensions.getByType()