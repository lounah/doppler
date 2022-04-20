plugins {
    `kotlin-dsl`
    id("convention.kotlin-jvm")
}

dependencies {
    implementation(libs.kotlinHtml)  {
        exclude(group = "org.jetbrains.kotlin")
    }
}

gradlePlugin {
    plugins {
        create("Doppler") {
            id = "me.lounah.doppler"
            implementationClass = "me.lounah.doppler.plugin.DuplicatedResourcesFinderPlugin"
            displayName = "Android Duplicated Resources Finder Plugin"
        }
    }
}