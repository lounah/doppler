package me.lounah.doppler.plugin.internal.reporter

import me.lounah.doppler.plugin.core.AndroidResource
import me.lounah.doppler.plugin.core.reporter.DuplicatedResources
import me.lounah.doppler.plugin.core.reporter.DuplicatedResourcesReporter
import java.io.File
import java.nio.file.Path

internal object SystemOutReporter : DuplicatedResourcesReporter {

    override fun render(resources: DuplicatedResources): String {
        return buildString {
            appendLine("[DuplicatedResourcesFinder] found ${resources.size} resources.")
            append(prepareReport(resources))
        }
    }

    override fun write(parent: File, resources: DuplicatedResources) {
        println(render(resources))
    }

    private fun prepareReport(resources: DuplicatedResources): String {
        return resources.entries.joinToString("\n") { (name, resources) ->
            val projects = resources.joinToString(transform = AndroidResource::project)
            "`$name` found in projects [$projects]"
        }
    }
}
