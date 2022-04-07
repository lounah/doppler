package me.lounah.doppler.plugin.core

public interface DuplicatedResourcesReporter {

    public fun report(resources: DuplicatedResources)

    public object SystemOut : DuplicatedResourcesReporter {

        override fun report(resources: DuplicatedResources) {
            val resourcesReport = resources.entries.joinToString("\n") { (key, resources) ->
                "`$key` of type `${resources.first().type}` found in projects [${resources
                    .joinToString(transform = AndroidResource::project)}]"
            }
            print(
                ("[DuplicatedResourcesFinder] found ${resources.size} resources.\n" +
                        resourcesReport).trim()
            )
        }
    }

    public object None : DuplicatedResourcesReporter {

        override fun report(resources: DuplicatedResources) {
            // do nothing
        }
    }
}
