import java.net.URI

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    `maven-publish`
}

group = extra["project.group"]!!
version = extra["project.version"]!!

kotlin {
    compilerOptions.freeCompilerArgs.add("-Xcontext-receivers")
    jvm()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlinx.serialization.core)
                implementation(libs.kotlinx.serialization.json)
                implementation(project(":common"))
                implementation(project(":media"))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.bundles.kotlinxSerializationCore)
                implementation(libs.bundles.kotlinxSerializationJson)
                implementation(libs.bundles.xmlutil.serialization)
                implementation(libs.bundles.xmlutil.core)
                implementation(libs.bundles.xmlutil.serialutil)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            version = extra["project.version"]!!.toString() + System.getenv("env")
            url = URI.create("https://maven.pkg.github.com/kshulzh/led4k")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}