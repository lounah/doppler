package me.lounah.doppler.plugin.internal.reporter.txt

import me.lounah.doppler.plugin.core.reporter.DuplicatedResources
import me.lounah.doppler.plugin.core.reporter.DuplicatedResourcesReporter
import me.lounah.doppler.plugin.internal.reporter.SystemOutReporter
import me.lounah.doppler.plugin.internal.reporter.html.HtmlReporter
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

internal object TxtReporter : DuplicatedResourcesReporter by SystemOutReporter {

    private const val RESULT_FILENAME = "doppler-report.txt"

    override fun write(parent: File, resources: DuplicatedResources) {
        if (!parent.exists()) parent.mkdirs()
        parent.resolve(RESULT_FILENAME).writeText(HtmlReporter.render(resources))
    }
}
