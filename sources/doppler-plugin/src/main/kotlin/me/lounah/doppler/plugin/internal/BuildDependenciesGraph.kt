package me.lounah.doppler.plugin.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency

internal class BuildDependenciesGraph : (Project) -> Set<Project> {

    private val visited = mutableMapOf<String, Set<Project>>()

    override fun invoke(target: Project): Set<Project> {
        return visited.getOrPut(target.name) {
            getSubprojects(target).flatMap(::invoke).toSet()
        }
            .minus(target)
    }

    private fun getSubprojects(project: Project): Set<Project> {
        return project.configurations
            .asSequence()
            .filterNot(::isTestConfiguration)
            .flatMap(Configuration::getDependencies)
            .map(Dependency::getName)
            .mapNotNull(project.rootProject::findProject)
            .toSet()
            .also { dependencies -> visited.put(project.name, dependencies) }
    }

    private fun isTestConfiguration(configuration: Configuration): Boolean {
        return configuration.name.startsWith("test") ||
                configuration.name.startsWith("androidTest")
    }
}