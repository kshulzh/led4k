buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.3.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    }
}
plugins {
    kotlin("multiplatform")
    id("com.android.library")
    //kotlin("android")
    `maven-publish`
}
android {
    namespace = "com.github.kshulzh.led4k.common"
    compileSdk = 34
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 34
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

group = extra["led4k.group"]!!
version = extra["led4k.version"]!!
kotlin {
    android()
    jvm {
        jvmToolchain(17)
        //withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
//            executable {
//                entryPoint = "main"
//            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${extra["kotlinx.version"]!!}")
                implementation("org.jetbrains.kotlin:kotlin-reflect:${extra["kotlin.version"]!!}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${extra["kotlinx.version"]!!}")
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-android:${extra["kotlinx.version"]!!}")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.appcompat:appcompat:1.6.1")
                implementation("androidx.activity:activity-compose:1.7.2")
                implementation("androidx.core:core-ktx:1.10.1")
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation("junit:junit:4.13.2")
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}

