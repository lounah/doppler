package me.lounah.doppler.plugin.core.reporter

import me.lounah.doppler.plugin.internal.reporter.SystemOutReporter
import me.lounah.doppler.plugin.internal.reporter.html.HtmlReporter
import me.lounah.doppler.plugin.internal.reporter.txt.TxtReporter
import java.io.File
import java.nio.file.Path

public interface DuplicatedResourcesReporter {

    public fun render(resources: DuplicatedResources): String

    public fun write(parent: File, resources: DuplicatedResources)

    public companion object {

        public fun systemOut(): DuplicatedResourcesReporter {
            return SystemOutReporter
        }

        public fun html(): DuplicatedResourcesReporter {
            return HtmlReporter
        }

        public fun txt(): DuplicatedResourcesReporter {
            return TxtReporter
        }

        public fun none(): DuplicatedResourcesReporter {
            return object : DuplicatedResourcesReporter {
                override fun render(resources: DuplicatedResources) = ""
                override fun write(parent: File, resources: DuplicatedResources) = Unit
            }
        }
    }
}
