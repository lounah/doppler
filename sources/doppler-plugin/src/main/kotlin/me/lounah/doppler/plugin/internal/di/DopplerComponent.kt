package me.lounah.doppler.plugin.internal.di

import me.lounah.doppler.plugin.core.DuplicatedResourcesFinder
import me.lounah.doppler.plugin.DuplicatedResourcesFinderExtension
import me.lounah.doppler.plugin.Reporter
import me.lounah.doppler.plugin.core.DuplicatedResourcesReporter
import me.lounah.doppler.plugin.internal.reporter.html.DuplicatedResourcesHtmlReporter
import me.lounah.doppler.plugin.internal.reporter.txt.DuplicatedResourcesTxtReporter
import org.gradle.api.Project

public abstract class DopplerComponent {

    internal abstract val duplicatedResourcesFinder: DuplicatedResourcesFinder

    internal abstract val duplicatedResourcesReporter: DuplicatedResourcesReporter
}

internal fun DopplerComponent(project: Project): DopplerComponent {
    return object : DopplerComponent() {
        private val extension = DuplicatedResourcesFinderExtension.create(project)

        override val duplicatedResourcesFinder: DuplicatedResourcesFinder
            get() = DuplicatedResourcesFinder.Impl(
                excludedProjects = extension.exclude.excludedProjects,
                excludedResources = extension.exclude.excludedResources,
                excludedSourceSets = extension.exclude.excludedSourceSets,
            )

        override val duplicatedResourcesReporter: DuplicatedResourcesReporter
            get() = when(extension.report.reporter) {
                is Reporter.Html -> {
                    DuplicatedResourcesHtmlReporter((extension.report.reporter as Reporter.Html).dest)
                }
                is Reporter.Txt -> {
                    DuplicatedResourcesTxtReporter((extension.report.reporter as Reporter.Txt).dest)
                }
                is Reporter.SystemOut -> DuplicatedResourcesReporter.SystemOut
                else -> DuplicatedResourcesReporter.None
            }
    }
}