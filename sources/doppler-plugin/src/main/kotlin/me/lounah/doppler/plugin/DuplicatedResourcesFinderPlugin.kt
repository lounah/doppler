package me.lounah.doppler.plugin

import me.lounah.doppler.plugin.internal.di.DopplerComponent
import org.gradle.api.Plugin
import org.gradle.api.Project

public class DuplicatedResourcesFinderPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        require(target.isAndroidApplication()) {
            "`me.lounah.duplicated-resources-finder` must be applied only to an Android application."
        }

        target.tasks.register(
            "findAndroidResourceDuplicates",
            DuplicatedResourcesFinderTask::class.java,
            DopplerComponent(target)
        ).configure {
            group = "verification"
        }
    }

    private fun Project.isAndroidApplication(): Boolean {
        return pluginManager.hasPlugin("com.android.application")
    }
}