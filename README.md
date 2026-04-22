# led4k

A Kotlin Multiplatform library for controlling LED displays. Supports JVM, iOS, macOS, Linux, and Windows.

## Installation

Add the dependency to your `build.gradle.kts`:

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    // Core abstractions (Program, Area, Media, Controller)
    implementation("io.github.kshulzh.led4k:common:0.0.2")

    // HD fullcolor device implementation
    implementation("io.github.kshulzh.led4k:hd-fullcolor:0.0.2")
}
```

For Kotlin Multiplatform projects:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.kshulzh.led4k:common:0.0.2")
            implementation("io.github.kshulzh.led4k:hd-fullcolor:0.0.2")
        }
    }
}
```

## Quick Start

```kotlin
import io.github.kshulzh.led4k.common.builder.image
import io.github.kshulzh.led4k.common.builder.location
import io.github.kshulzh.led4k.common.builder.program
import io.github.kshulzh.led4k.common.builder.size
import io.github.kshulzh.led4k.common.builder.video
import io.github.kshulzh.led4k.common.media.PathMedia
import io.github.kshulzh.led4k.hd.fullcolor.controller.HDController
import kotlinx.coroutines.runBlocking

fun main() {
    val program = program {
        video {
            media = PathMedia("video1.mp4")
            size(128, 160)
            location(0, 0)
        }
        image {
            media = PathMedia("image1.png")
            size(128, 128)
            location(0, 0)
        }
    }

    runBlocking {
        val controller = HDController("192.168.1.100", model = "C16L", width = 128, height = 160)
        controller.upload(program)
    }
}
```

## Modules

### `common`

Device-agnostic abstractions:

- **`Program`** / **`Area`** model — hierarchical content structure
- **Builder DSL** — fluent API for constructing programs
- **`Controller`** interface — `suspend fun upload(program: Program)`
- **Effect / PlayMode / Schedule** types

### `hd-fullcolor`

Implementation for HD fullcolor LED devices:

- **`HDController`** — communicates with the device over TCP/UDP
- **`ProgramTransformer`** — converts a generic `Program` into device-specific binary format

## Usage

### Building a Program

Use the builder DSL from `common` to compose a `Program` with `video` and `image` areas:

```kotlin
val program = program {
    // Full-screen video
    video {
        media = PathMedia("promo.mp4")
        size(128, 160)
        location(0, 0)
    }

    // Image overlay in the lower half
    image {
        media = PathMedia("banner.png")
        size(128, 80)
        location(0, 80)
    }
}
```

### Uploading to a Device

Instantiate `HDController` with the device address and display dimensions, then call `upload`:

```kotlin
val controller = HDController(
    address = "192.168.1.100",
    port = 9527,          // default
    width = 128,
    height = 160,
    model = "C16L"
)

runBlocking {
    controller.upload(program)
}
```

### In-memory Media

Use `BufferMedia` to pass media as raw bytes instead of file paths:

```kotlin
import io.github.kshulzh.led4k.common.media.BufferMedia
import kotlinx.io.Buffer

val buffer = Buffer()
buffer.write(imageBytes)

val program = program {
    image {
        media = BufferMedia(buffer)
        size(128, 128)
        location(0, 0)
    }
}
```

### Play Modes

```kotlin
import io.github.kshulzh.led4k.common.mode.FixedTimesPlayMode
import io.github.kshulzh.led4k.common.mode.FixedDurationPlayMode

val program = program {
    playMode = FixedTimesPlayMode(times = 3)   // play 3 times
    // or
    playMode = FixedDurationPlayMode(ms = 30_000) // play for 30 seconds

    image {
        media = PathMedia("slide.png")
        size(128, 128)
        location(0, 0)
    }
}
```

### Scheduling

```kotlin
import io.github.kshulzh.led4k.common.schedule.DurationSchedule
import io.github.kshulzh.led4k.common.schedule.WeekSchedule

// Play between two timestamps
val program = program {
    schedule = DurationSchedule(startMs = startEpochMs, endMs = endEpochMs)
    // ...
}

// Play on specific weekdays
val program = program {
    schedule = WeekSchedule(monday = true, wednesday = true, friday = true)
    // ...
}
```

## Building from Source

```bash
./gradlew clean build
```

Run a specific test class:

```bash
./gradlew :hd-fullcolor:allTests --tests "io.github.kshulzh.led4k.hd.fullcolor.controller.ProgramTransformerTest"
./gradlew :common:allTests --tests "io.github.kshulzh.led4k.builder.BuilderTest"
```

## License

Licensed under the [Apache License 2.0](LICENSE).
