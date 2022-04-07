@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("convention.kotlin-base")
    id("convention.android-base")
}

android {
    resourcePrefix = project.name
        .replace("feature-", "")
        .replace("common-", "")
        .replace("-", "_")
        .plus("_")

    buildFeatures.buildConfig = true

    variantFilter {
        if (buildType.name == "debug") {
            ignore = true
        }
    }
}
