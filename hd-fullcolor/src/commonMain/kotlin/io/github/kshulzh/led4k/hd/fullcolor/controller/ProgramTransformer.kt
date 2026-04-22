/*
 * Copyright (c) 2026. Kirill Shulzhenko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.kshulzh.led4k.hd.fullcolor.controller

import io.github.kshulzh.led4k.common.area.BaseArea
import io.github.kshulzh.led4k.common.area.ImageArea
import io.github.kshulzh.led4k.common.area.VideoArea
import io.github.kshulzh.led4k.common.media.BufferMedia
import io.github.kshulzh.led4k.common.media.Media
import io.github.kshulzh.led4k.common.media.PathMedia
import io.github.kshulzh.led4k.common.media.name
import io.github.kshulzh.led4k.common.mode.FixedDurationPlayMode
import io.github.kshulzh.led4k.common.mode.FixedTimesPlayMode
import io.github.kshulzh.led4k.common.program.ListProgram
import io.github.kshulzh.led4k.common.program.Program
import io.github.kshulzh.led4k.common.program.SingleProgram
import io.github.kshulzh.led4k.common.program.SingleProgramImpl
import io.github.kshulzh.led4k.common.schedule.DurationSchedule
import io.github.kshulzh.led4k.common.schedule.WeekSchedule
import io.github.kshulzh.led4k.common.utils.MD5
import io.github.kshulzh.led4k.hd.fullcolor.media.HDMedia
import io.github.kshulzh.led4k.hd.fullcolor.model.FileItem
import io.github.kshulzh.led4k.hd.fullcolor.model.FileList
import io.github.kshulzh.led4k.hd.fullcolor.model.HDControllerPlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDFramePlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDOrdinaryScenePlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDPhotoPlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDVideoPlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.toInt
import io.github.kshulzh.led4k.hd.fullcolor.model.toTime
import io.github.kshulzh.led4k.hd.fullcolor.model.write
import io.github.kshulzh.led4k.hd.fullcolor.schedule.HDSchedule
import kotlinx.io.Buffer
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteArray

/**
 * A utility class responsible for transforming and processing program structures into
 * compatible high-definition media plugins.
 *
 * This class encapsulates the logic required to convert various program configurations
 * (e.g., single programs or composite programs) into plugin-compatible formats, while handling
 * underlying media operations such as checksum computation and file metadata management.
 *
 * @property path The root directory for processing and storage.
 * @property width The default width for media content, in pixels.
 * @property height The default height for media content, in pixels.
 * @property appVersion The version of the application processing the programs.
 * @property model The device model used for media content compatibility.
 * @constructor Initializes the transformer and ensures that the directory at the given path exists.
 */
class ProgramTransformer(
    val path: Path,
    val width: Int = 128,
    val height: Int = 128,
    val appVersion: String = "7.10.2.0",
    val model: String
) {
    init {
        SystemFileSystem.createDirectories(path)
    }

    /**
     * A mutable map that stores associations between string keys and high-definition media objects.
     *
     * This map is used for managing and organizing media files, where:
     * - The key represents a unique identifier (e.g., file name or hash) for each media file.
     * - The value is an instance of `HDMedia`, which contains detailed metadata and content for the media.
     *
     * The `files` map is particularly useful in the context of transformations and uploads,
     * allowing efficient access and manipulation of media assets.
     */
    val files = mutableMapOf<String, HDMedia>()
    /**
     * The main high-definition media object associated with the current program transformation.
     *
     * This variable is used to store the primary media file that is processed during
     * the transformation of a program and is utilized in subsequent upload operations.
     *
     * It is expected to be initialized by the `ProgramTransformer` during the transformation
     * process, and represents the main output media file resulting from these operations.
     *
     * @see ProgramTransformer.processVideoArea
     * @see HDMedia
     */
    lateinit var main: HDMedia

    /**
     * Processes a given `Program` instance and transforms it into the appropriate internal structure
     * or configuration. Supports handling of both `SingleProgram` and `ListProgram` types.
     *
     * If the program is a `SingleProgram`, it is directly processed, and its result is wrapped
     * in a list. For `ListProgram`, all `SingleProgram` instances within it are processed individually,
     * and their results are aggregated. Other types of `Program` are not supported and will result in
     * an exception.
     *
     * @param program the `Program` instance to be processed. Can be either a `SingleProgram` or a `ListProgram`.
     * @throws UnsupportedOperationException if the provided `Program` is neither `SingleProgram` nor `ListProgram`.
     */
    fun process(program: Program) {

        val programs = when (program) {
            is SingleProgram -> {
                listOf(process(program))
            }

            is ListProgram -> {
                program.programs.filterIsInstance<SingleProgram>().map { process(it) }
            }

            else -> {
                throw UnsupportedOperationException()
            }
        }

        val hdControllerPlugin = HDControllerPlugin(
            deviceModel = model,
            width = width,
            height = height,
            appVersion = appVersion,
            nodes = programs.toMutableList()
        )

        val main = BufferMedia(
            Buffer().apply {
                write(hdControllerPlugin)
            }
        )
        val md5 = main.source().buffered().use { it.readByteArray().MD5().toHexString() }
        this.main = HDMedia(main, md5, "boo", main.buffer.size)
    }

    /**
     * Processes a given `SingleProgram` and generates an instance of `HDOrdinaryScenePlugin`.
     *
     * The method transforms the `SingleProgram` into a corresponding scene plugin by processing
     * its areas and configuring the plugin's properties such as play mode, scheduling, and runtime settings.
     *
     * @param program The `SingleProgram` instance to be processed. This represents a single behavior or
     *                configuration of areas that need transformation into an HD scene plugin.
     * @return An instance of `HDOrdinaryScenePlugin` representing the transformed result of the
     *         provided `SingleProgram`. This plugin contains nodes, play mode configurations,
     *         schedule settings, and runtime parameters derived from the input.
     */
    fun process(program: SingleProgram): HDOrdinaryScenePlugin {
        val subnodes = program.areas.mapNotNull {
            if (it is BaseArea) {
                process(it)
            } else {
                null
            }
        }

        return HDOrdinaryScenePlugin(
            nodes = subnodes.toMutableList()
        ).also {
            if (program is SingleProgramImpl) {
                when (val playMode = program.playMode) {
                    is FixedTimesPlayMode -> {
                        it.playMode = "LoopTime"
                        it.playTimes = playMode.times
                    }

                    is FixedDurationPlayMode -> {
                        it.playMode = "FixedTime"
                        it.playeTime = (playMode.ms / 1000).toInt()
                    }
                }

                val hdSchedule = when (val schedule= program.schedule) {
                    is DurationSchedule -> HDSchedule(
                        schedule
                    )

                    is WeekSchedule -> HDSchedule(
                        weekSchedule = schedule
                    )

                    is HDSchedule -> schedule
                    else -> throw RuntimeException("Unsupported ${schedule::class}")
                }
                it.monday = hdSchedule.weekSchedule.monday.toInt()
                it.tuesday = hdSchedule.weekSchedule.tuesday.toInt()
                it.wednesday = hdSchedule.weekSchedule.wednesday.toInt()
                it.thursday = hdSchedule.weekSchedule.thursday.toInt()
                it.friday = hdSchedule.weekSchedule.friday.toInt()
                it.saturday = hdSchedule.weekSchedule.saturday.toInt()
                it.sunday = hdSchedule.weekSchedule.sunday.toInt()
                it.spaceStartTime = hdSchedule.durationSchedule.start.toTime()
                it.spaceStopTime = hdSchedule.durationSchedule.end.toTime()
            }
        }
    }

    /**
     * Processes the given `BaseArea` and converts it into an `HDFramePlugin`.
     * Depending on the specific type of the `BaseArea` (e.g., `VideoArea` or `ImageArea`),
     * it invokes the appropriate processing method and constructs a node structure for the plugin.
     *
     * @param baseArea The base area to be processed. Must be either a `VideoArea` or `ImageArea`.
     * @return An `HDFramePlugin` containing the processed configuration and associated nodes.
     * @throws UnsupportedOperationException If the provided `BaseArea` is of an unsupported type.
     */
    fun process(baseArea: BaseArea): HDFramePlugin {
        val nodes = when (baseArea) {
            is VideoArea -> {
                listOf(processVideoArea(baseArea))
            }

            is ImageArea -> {
                listOf(processImageArea(baseArea))
            }

            else -> {
                throw UnsupportedOperationException()
            }
        }

        return HDFramePlugin(
            x = baseArea.x,
            y = baseArea.y,
            width = baseArea.width,
            height = baseArea.height,
            nodes = nodes.toMutableList()
        )

    }

    /**
     * Processes a given video area and returns a node associated with an HDFramePlugin.
     *
     * This method takes a `VideoArea` object, utilizes its media content, and integrates
     * it into a `HDFramePlugin`. A single file is processed and wrapped into a `FileList`
     * that is subsequently associated with a newly created `HDVideoPlugin`. The method
     * ultimately returns the `Node` object that is part of the `HDFramePlugin` structure.
     *
     * @param area An instance of `VideoArea` representing the area containing video media to be processed.
     * @return A `HDFramePlugin.Node` instance associated with the processed video area.
     */
    fun processVideoArea(area: VideoArea): HDFramePlugin.Node {
        val f = put(area.media, "Video")
        return HDVideoPlugin(
            fileList = FileList(mutableListOf(f)),
        )
    }

    /**
     * Processes the given `ImageArea` instance and generates a node for the HDFramePlugin.
     *
     * @param area The `ImageArea` instance containing image-based media to be processed.
     * @return A `Node` instance associated with the `HDFramePlugin` that encapsulates the processed image media.
     */
    fun processImageArea(area: ImageArea): HDFramePlugin.Node {
        val f = put(area.media, "Photo")
        return HDPhotoPlugin(
            fileList = FileList(mutableListOf(f)),
        )
    }

    /**
     * Stores a media file in the internal file management system and returns its metadata.
     *
     * This method computes the MD5 checksum of the provided media file, determines its
     * size, and extracts its file extension. It then stores the media along with its
     * metadata in the internal file storage. Finally, it provides a `FileItem` instance
     * containing the relevant file information.
     *
     * @param file The media file to be stored. It must implement the `Media` interface
     * and contain a valid source of raw content.
     * @param key A unique identifier associated with the file. This key is used to
     * distinguish the file in the system.
     * @return A `FileItem` instance containing the MD5 checksum, filename, and the
     * supplied key that uniquely identifies the file.
     * @throws RuntimeException If the provided media file does not have a valid name.
     */
    fun put(file: Media, key: String): FileItem {
        var size = 0
        val md5 = file.source().use {
            val readByteArray = it.buffered().readByteArray()
            size = readByteArray.size
            readByteArray.MD5().toHexString()
        }
        val originalName = if (file is PathMedia) {
            file.path.name
        } else {
            file.name ?: throw RuntimeException("Media must have name")
        }

        val lastDotPosition = originalName.lastIndexOf('.')
        if (lastDotPosition < 0) {
            throw RuntimeException("Media must have extension")
        }

        val extension = originalName.substring(lastDotPosition + 1)
        files[md5] = HDMedia(media = file, md5 = md5, extension = extension, size = size.toLong())

        return FileItem(md5, "$md5.$extension", key)
    }
}