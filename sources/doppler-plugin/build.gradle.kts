plugins {
    `kotlin-dsl`
    id("convention.kotlin-jvm")
}

gradlePlugin {
    plugins {
        create("Doppler") {
            id = "me.lounah.duplicated-resources-finder"
            implementationClass = "me.lounah.doppler.plugin.DuplicatedResourcesFinderPlugin"
            displayName = "Android Duplicated Resources Finder Plugin"
        }
    }
}