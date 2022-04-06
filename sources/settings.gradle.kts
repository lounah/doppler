@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "doppler"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-dependencies")
    id("convention-plugins")
}

includeBuild("../build-conventions")