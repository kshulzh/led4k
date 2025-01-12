@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.vanniktech.maven.publish)
}

group = extra["project.group"]!!
version = extra["project.version"]!!

kotlin {
    jvm()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    macosX64()
    macosArm64()

    linuxX64()
    linuxArm64()

    mingwX64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.io.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks.named("jvmJar", Jar::class) {
    archiveBaseName.set("led4k-common")
}
mavenPublishing {
    publishToMavenCentral(false)
    coordinates(group.toString(), "common", version.toString())

    pom {
        name = "Led4k Common module"
        description = "Module contains basic classes and functionality"
        inceptionYear = "2026"
        url = "https://github.com/kshulzh/led4k"
        licenses {
            license {
                name = "The Apache License, Version 2.0"
                url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                distribution = "https://www.apache.org/licenses/LICENSE-2.0.txt"
            }
        }
        developers {
            developer {
                id = "kshulzh"
                name = "Kirill Shulzhenko"
                url = "https://github.com/kshulzh/"
                email = "kirill.shulzhenko2000@gmail.com"
                organization = "kshulzh"
                organizationUrl = "https://github.com/kshulzh"

            }
        }
        scm {
            url = "https://github.com/kshulzh/led4k"
            connection = "scm:git:git://github.com/kshulzh/led4k.git"
            developerConnection = "scm:git:ssh://git@github.com/kshulzh/led4k.git"
        }
    }
}