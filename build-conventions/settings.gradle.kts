@file:Suppress("UnstableApiUsage")

rootProject.name = "build-conventions"

pluginManagement {
    includeBuild("../build-settings")
}

plugins {
    id("convention-plugins")
    id("convention-dependencies")
}

include("android")
include("kotlin")
include("testing")
include("util")
include("publishing")