# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Build and test
./gradlew clean build

# Publish to Maven Central (requires GPG signing credentials)
./gradlew publishToMavenCentral --no-configuration-cache -PsignAllPublications=true
```

Run a single test class:
```bash
./gradlew :hd-fullcolor:allTests --tests "io.github.kshulzh.led4k.hd.fullcolor.controller.ProgramTransformerTest"
./gradlew :common:allTests --tests "io.github.kshulzh.led4k.builder.BuilderTest"
```

## Architecture

This is a Kotlin Multiplatform library (JVM, iOS, macOS, Linux, Windows) for controlling LED displays. Published to Maven Central as `io.github.kshulzh.led4k`.

**Two modules:**

- **`common`** — device-agnostic abstractions: `Program`/`Area` model, builder DSL, `Controller` interface, effect/schedule/mode types
- **`hd-fullcolor`** — implementation for HD fullcolor LED devices: `HDController` (communicates over TCP/UDP), `ProgramTransformer` (converts generic `Program` → device-specific binary format)

**Typical usage flow:**
1. Build a `Program` using the DSL in `common/builder/builders.kt`
2. Add `ImageArea`/`VideoArea` regions with media and effects
3. Instantiate `HDController(address, port, width, height, ...)`
4. Call `suspend fun upload(program: Program)` to send to the physical device

**Key design pattern:** `ProgramTransformer` in `hd-fullcolor` walks the generic program tree and produces `HDPlugin` objects (photo/video/frame plugins) that map to the device's wire protocol.

## Project Metadata

- Version: `0.0.2-SNAPSHOT` (in `gradle.properties`)
- Group: `io.github.kshulzh.led4k`
- Kotlin: `2.3.0`, Ktor: `3.4.2`, kotlinx-io: `0.9.0`
- Code style: `kotlin.code.style=official`
- CI runs on self-hosted runners with JDK 17
