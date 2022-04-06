import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("convention.unit-testing")
}

tasks {
    withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs = freeCompilerArgs +
                    "-Xopt-in=kotlin.RequiresOptIn" +
                    "-progressive"
        }
    }
}