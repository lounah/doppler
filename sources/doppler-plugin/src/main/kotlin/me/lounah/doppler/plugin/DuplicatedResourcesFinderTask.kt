package me.lounah.doppler.plugin

import me.lounah.doppler.plugin.internal.di.DopplerComponent
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

public open class DuplicatedResourcesFinderTask @Inject constructor(
    private val component: DopplerComponent
) : DefaultTask() {

    init {
        description = "Gradle task, which locates & report duplicated resources in Android apps."
    }

    @TaskAction
    public fun run() {
        component.duplicatedResourcesFinder.find(project)
            .also(component.duplicatedResourcesReporter::report)
    }
}
