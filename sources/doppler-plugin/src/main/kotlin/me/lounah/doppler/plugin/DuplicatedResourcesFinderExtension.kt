package me.lounah.doppler.plugin

import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.util.internal.ConfigureUtil
import java.io.File

public open class DuplicatedResourcesFinderExtension(
    public var exclude: ExcludesConfiguration = ExcludesConfiguration(),
    public var report: ReportConfiguration = ReportConfiguration()
) {

    /**
     * Configure, which types of resources should be bypassed by plugin.
     * You can either exclude <code>projects</code>, <code>source sets</code>,
     * or concrete <code>resource</code> files.
     *
     * @see me.lounah.doppler.plugin.ExcludesConfiguration
     */
    public fun exclude(block: ExcludesConfiguration.() -> Unit) {
        exclude = exclude.apply(block)
    }

    /**
     * Configure, which types of resources should be bypassed by plugin.
     * You can either exclude <code>projects</code>, <code>source sets</code>,
     * or concrete <code>resource</code> files.
     *
     * @see me.lounah.doppler.plugin.ExcludesConfiguration
     */
    public fun exclude(closure: Closure<*>) {
        ConfigureUtil.configure(closure, exclude)
    }

    /**
     * Configure, which type of reporter should be used by plugin.
     * You can either use <code>.html</code> report, <code>.txt</code>,
     * <code>console</code> or no report at all.
     *
     * @see me.lounah.doppler.plugin.ReportConfiguration
     */
    public fun report(block: ReportConfiguration.() -> Unit) {
        report = report.apply(block)
    }

    /**
     * Configure, which type of reporter should be used by plugin.
     * You can either use <code>.html</code> report, <code>.txt</code>,
     * <code>console</code> or no report at all.
     *
     * @see me.lounah.doppler.plugin.ReportConfiguration
     */
    public fun report(closure: Closure<*>) {
        ConfigureUtil.configure(closure, report)
    }

    internal companion object {

        internal fun create(project: Project): DuplicatedResourcesFinderExtension {
            return project.extensions.create("doppler")
        }
    }
}

public class ExcludesConfiguration(
    public var excludedProjects: Set<String> = emptySet(),
    public var excludedSourceSets: Set<String> = emptySet(),
    public var excludedResources: Set<String> = setOf("app_name", "file_provider"),
) {

    /**
     * Exclude projects from plugin checks by their names.
     * <code>
     *     doppler {
     *          exclude {
     *              projects("some-project-1", "some-project-2")
     *          }
     *     }
     * </code>
     */
    public fun projects(vararg projects: String) {
        excludedProjects += projects
    }

    /**
     * Exclude projects from plugin checks by their names.
     * <code>
     *     doppler {
     *          exclude {
     *              sourceSets("androidTest", "test")
     *          }
     *     }
     * </code>
     */
    public fun sourceSets(vararg sourceSets: String) {
        excludedSourceSets += sourceSets
    }

    /**
     * Exclude projects from plugin checks by their names.
     * <code>
     *     doppler {
     *          exclude {
     *              resources("app_name", "network_security_config.xml")
     *          }
     *     }
     * </code>
     *
     * <code>app_name</code> and <code>file_provider</code> are excluded by default.
     */
    public fun resources(vararg resources: String) {
        excludedResources += resources
    }
}

public class ReportConfiguration(
    public var reporter: Reporter = Reporter.SystemOut
) {

    /**
     * Prepare <code>html</code> report and put it in <code>destination</code> folder.
     * @param destination directory, in which plugin will produce the report.
     * <code>project.buildDirectory/reports/doppler</code> by default.
     */
    public fun html(destination: File) {
        reporter = Reporter.Html(destination)
    }

    /**
     * Prepare <code>txt</code> report and put it in <code>destination</code> folder.
     * @param destination directory, in which plugin will produce the report.
     * <code>project.buildDirectory/reports/doppler</code> by default.
     */
    public fun txt(destination: File) {
        reporter = Reporter.Txt(destination)
    }

    public fun systemOut() {
        reporter = Reporter.SystemOut
    }

    public fun none() {
        reporter = Reporter.None
    }
}

public sealed class Reporter {

    public class Html(public val dest: File): Reporter()

    public class Txt(public val dest: File): Reporter()

    public object SystemOut: Reporter()

    public object None : Reporter()
}
