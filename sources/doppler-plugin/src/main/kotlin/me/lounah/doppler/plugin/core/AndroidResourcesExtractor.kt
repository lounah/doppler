package me.lounah.doppler.plugin.core

import org.gradle.api.Project
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory

public interface AndroidResourcesExtractor {

    public fun extract(project: Project): List<AndroidResource>

    public companion object {

        public fun impl(): AndroidResourcesExtractor {
            val resourceValues = setOf("**/res/values/*.xml", "**/res/values-*/*.xml")
            val resources = setOf("**/res/**/*.xml", "**/res/**/*.webp", "**/res/**/*.png")

            return object : AndroidResourcesExtractor {
                override fun extract(project: Project): List<AndroidResource> {
                    return DefaultExtractor(resourceValues, resources).extract(project) +
                            ValuesExtractor(resourceValues).extract(project)
                }
            }
        }
    }

    private class DefaultExtractor(
        private val resourceValues: Set<String>,
        private val resources: Set<String>
    ) : AndroidResourcesExtractor {

        override fun extract(project: Project): List<AndroidResource> {
            return project.layout.projectDirectory.asFileTree
                .matching { include(resources).exclude(resourceValues) }
                .map { resource ->
                    AndroidResource(
                        name = resource.name,
                        project = project.name,
                        path = resource.path
                    )
                }
        }
    }

    private class ValuesExtractor(
        private val resourceValues: Set<String>
    ) : AndroidResourcesExtractor {

        private val documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder()

        override fun extract(project: Project): List<AndroidResource> {
            return project.layout.projectDirectory.asFileTree
                .matching { include(resourceValues) }
                .flatMap { resource -> parseResourceValue(project, resource) }
        }

        private fun parseResourceValue(project: Project, resource: File): List<AndroidResource> {
            val values = documentBuilder.parse(resource).documentElement.childNodes
            return (0 until values.length)
                .asSequence()
                .map(values::item)
                .filter(Node::hasAttributes)
                .map { node ->
                    AndroidResource(
                        name = node.attributes.getNamedItem("name").nodeValue,
                        project = project.name,
                        path = resource.path
                    )
                }
                .toList()
        }
    }
}
