plugins {
    id("convention.android-application")
    id("me.lounah.duplicated-resources-finder")
}

doppler {
    exclude {
        resources("shared_excluded_resource.xml")
        sourceSets("androidTest")
    }
    report {
        html()
    }
}

dependencies {
    implementation(projects.androidLibrary1)
    implementation(projects.androidLibrary2)
}