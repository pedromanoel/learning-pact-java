plugins {
    id("com.github.johnrengelman.shadow") version "5.2.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.3.71" apply false
    id("au.com.dius.pact") version "4.0.8" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}