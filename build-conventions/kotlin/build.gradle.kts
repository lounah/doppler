@file:Suppress("UnstableApiUsage")

plugins {
    `kotlin-dsl`
}

group = "me.lounah.doppler.buildconventions"

dependencies {
    implementation(projects.testing)
    implementation(libs.kotlinGradle)
    // workaround for https://github.com/gradle/gradle/issues/15383
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}