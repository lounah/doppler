@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "me.lounah.doppler.buildconventions"

dependencies {
    implementation(projects.util)
    implementation(libs.kotlinGradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}