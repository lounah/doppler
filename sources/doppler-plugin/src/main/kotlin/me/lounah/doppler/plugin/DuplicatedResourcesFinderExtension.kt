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

    public fun exclude(block: ExcludesConfiguration.() -> Unit) {
        exclude = exclude.apply(block)
    }

    public fun exclude(closure: Closure<*>) {
        ConfigureUtil.configure(closure, exclude)
    }

    public fun report(block: ReportConfiguration.() -> Unit) {
        report = report.apply(block)
    }

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

    public fun projects(vararg projects: String) {
        excludedProjects += projects
    }

    public fun sourceSets(vararg sourceSets: String) {
        excludedSourceSets += sourceSets
    }

    public fun resources(vararg resources: String) {
        excludedResources += resources
    }
}

public class ReportConfiguration(
    public var reporter: Reporter = Reporter.SystemOut
) {

    public fun html(destination: File) {
        reporter = Reporter.Html(destination)
    }

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
