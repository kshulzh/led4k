pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        maven("https://maven.google.com")
    }

    plugins {
        kotlin("multiplatform").version(extra["kotlin.version"] as String)
        kotlin("plugin.serialization").version(extra["kotlin.version"] as String)
        // kotlin("android").version(extra["kotlin.version"] as String)
        //id("com.android.application").version(extra["agp.version"] as String)
        id("com.google.devtools.ksp") version "1.9.0-1.0.13"
        id("com.android.library").version(extra["agp.version"] as String)
    }
}
rootProject.name = "led4k"
include("common")
include("xml")
include("hd-fullcolor", "hd-fullcolor:hd-fullcolor-ksp")
