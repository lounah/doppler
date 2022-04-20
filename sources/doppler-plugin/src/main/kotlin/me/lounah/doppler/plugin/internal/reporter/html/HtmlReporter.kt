package me.lounah.doppler.plugin.internal.reporter.html

import kotlinx.html.*
import kotlinx.html.stream.createHTML
import me.lounah.doppler.plugin.core.AndroidResource
import me.lounah.doppler.plugin.core.reporter.DuplicatedResources
import me.lounah.doppler.plugin.core.reporter.DuplicatedResourcesReporter
import java.io.BufferedReader
import java.io.File

internal object HtmlReporter : DuplicatedResourcesReporter {

    private const val DEFAULT_TEMPLATE = "default-html-template.html"
    private const val PLACEHOLDER_RESOURCES = "@@@resources@@@"
    private const val RESULT_FILENAME = "doppler-report.html"

    override fun render(resources: DuplicatedResources): String {
        return javaClass.getResource("/$DEFAULT_TEMPLATE")!!
            .openConnection()
            .apply { useCaches = false }
            .getInputStream()
            .bufferedReader()
            .use(BufferedReader::readText)
            .replace(PLACEHOLDER_RESOURCES, renderReport(resources))
    }

    override fun write(parent: File, resources: DuplicatedResources) {
        if (!parent.exists()) parent.mkdirs()
        parent.resolve(RESULT_FILENAME).writeText(render(resources))
    }

    private fun renderReport(report: DuplicatedResources) = createHTML().div {
        ul {
            report.forEach { (name, resources) ->
                val projects = resources.joinToString(transform = AndroidResource::project)
                li {
                    code { b { text(name) } }
                    text(" found in projects [$projects]")
                }
            }
        }
    }
}
