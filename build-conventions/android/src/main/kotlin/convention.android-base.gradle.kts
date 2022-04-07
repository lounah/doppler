import com.android.build.gradle.BaseExtension

extensions.configure<BaseExtension> {

    sourceSets {
        named("main").configure { java.srcDir("src/main/kotlin") }
        named("androidTest").configure { java.srcDir("src/androidTest/kotlin") }
        named("test").configure { java.srcDir("src/test/kotlin") }
    }

    // workaround for https://github.com/gradle/gradle/issues/15383
    if (project.name != "gradle-kotlin-dsl-accessors") {
        val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

        compileSdkVersion(libs.versions.compileSdk.get().toInt())

        defaultConfig {
            minSdk = libs.versions.minSdk.get().toInt()
            targetSdk = libs.versions.targetSdk.get().toInt()
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    lintOptions {
        isAbortOnError = false
        isWarningsAsErrors = true
        htmlReport = true
        isQuiet = true
    }

    packagingOptions.resources.pickFirsts.add("META-INF/**")

    @Suppress("UnstableApiUsage")
    with(buildFeatures) {
        compose = false
        buildConfig = true
        renderScript = true
        viewBinding = true
    }
}
