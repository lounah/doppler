# __Doppler__

---
Gradle plugin, which detects & reports about duplicated resources in <code>Android</code> projects.

![doppler in action](doc/report.png "doppler in action")

### Features
- Easy configuration
- Exclude projects, sourceSets, concrete resources from plugin checks
- Html, Txt, Console reports

### Quick start

#### with Gradle

```kotlin
plugins {
    id("me.lounah.duplicated-resources-finder").version("[version]")
}

repositories {
    mavenCentral()
}

doppler {
    exclude {
        resources("shared_excluded_resource.xml")
        sourceSets("androidTest")
    }
    report {
        systemOut()
    }
}
```