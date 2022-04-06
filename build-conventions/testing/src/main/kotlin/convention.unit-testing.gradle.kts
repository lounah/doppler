import me.lounah.doppler.build.util.withVersionCatalog
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePluginWrapper

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    failFast = false
}

project.withVersionCatalog { libs ->
    plugins.withType<KotlinBasePluginWrapper> {
        dependencies {
        }
    }
}