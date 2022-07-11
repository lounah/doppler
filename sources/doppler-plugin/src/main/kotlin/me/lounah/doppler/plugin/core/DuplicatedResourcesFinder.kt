package me.lounah.doppler.plugin.core

import me.lounah.doppler.plugin.core.reporter.DuplicatedResources
import me.lounah.doppler.plugin.internal.BuildDependenciesGraph
import org.gradle.api.Project

public interface DuplicatedResourcesFinder {

    public fun find(project: Project): DuplicatedResources

    public class Impl(
        private val excludedProjects: Set<String>,
        private val excludedSourceSets: Set<String>,
        private val excludedResources: Set<String>,
        private val resourcesExtractor: AndroidResourcesExtractor = AndroidResourcesExtractor.impl(),
        private val buildDependenciesGraph: (Project) -> Set<Project> = BuildDependenciesGraph()
    ) : DuplicatedResourcesFinder {

        override fun find(project: Project): DuplicatedResources {
            return buildDependenciesGraph(project)
                .asSequence()
                .filter(::isResourceCheckEnabled)
                .flatMap(resourcesExtractor::extract)
                .groupBy(AndroidResource::name)
                .mapValues { (_, resources) ->
                    resources.distinctBy(AndroidResource::project)
                        .filter { resource -> resource.sourceSet !in excludedSourceSets }
                }
                .filter { (name, resources) -> name !in excludedResources && resources.size > 1 }
                .toMap()
        }

        private fun isResourceCheckEnabled(project: Project): Boolean {
            return project.name !in excludedProjects
        }
    }
}
