package me.lounah.doppler.plugin

import me.lounah.doppler.plugin.internal.di.DopplerComponent
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Doppler is a Gradle plugin, which analyzes all Android application module dependencies
 * and finds duplicates in it.
 * It takes in count those types of resources:
 * <ol>
 *     <li>layout* resources</li>
 *     <li>drawable* resources</li>
 *     <li>xml* resources</li>
 *     <li>anim* resources</li>
 *     <li>color* resources</li>
 *     <li>menu* resources</li>
 *     <li>value* resources of all types (those, located in a /res/values-* folder)</li>
 * </ol>
 *
 * If any duplicates found, Doppler will produce a report of different types:
 * <ol>
 *     <li>Html</li>
 *     <li>Txt</li>
 *     <li>Console</li>
 * </ol>
 */
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