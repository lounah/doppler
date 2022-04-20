@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "me.lounah.doppler.buildconventions"

dependencies {
    implementation(projects.kotlin)
    implementation(projects.util)
    implementation(libs.kotlinGradle)
    implementation(libs.androidGradle)
    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}