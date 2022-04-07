package me.lounah.doppler.plugin.internal.reporter.txt

import me.lounah.doppler.plugin.core.DuplicatedResources
import me.lounah.doppler.plugin.core.DuplicatedResourcesReporter
import java.io.File

internal class DuplicatedResourcesTxtReporter(
    private val destination: File
) : DuplicatedResourcesReporter {

    override fun report(resources: DuplicatedResources) {
        TODO("Not yet implemented")
    }
}
