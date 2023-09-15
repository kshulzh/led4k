group = extra["led4k.group"]!!
version = extra["led4k.version"]!!
allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
        maven("https://maven.google.com")
    }
}
plugins {
    kotlin("multiplatform") version "1.9.0" apply false
    kotlin("android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    id("com.android.library") apply false
    //id("org.jetbrains.compose") apply false
    `maven-publish`
}
