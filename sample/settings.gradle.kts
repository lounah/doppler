@file:Suppress("UnstableApiUsage")

rootProject.name = "sample"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-dependencies")
    id("convention-plugins")
}

includeBuild("../build-conventions")
includeBuild("../sources")

include("android-application")
include("android-library-1")
include("android-library-2")
include("android-library-3")
include("android-library-4")