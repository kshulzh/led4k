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

import io.github.kshulzh.led4k.common.area.ImageArea
import io.github.kshulzh.led4k.common.area.ImageAreaImpl
import io.github.kshulzh.led4k.common.area.VideoArea
import io.github.kshulzh.led4k.common.area.VideoAreaImpl
import io.github.kshulzh.led4k.common.media.BufferMedia
import io.github.kshulzh.led4k.common.media.name
import io.github.kshulzh.led4k.common.mode.FixedDurationPlayMode
import io.github.kshulzh.led4k.common.mode.FixedTimesPlayMode
import io.github.kshulzh.led4k.common.program.ListProgramImpl
import io.github.kshulzh.led4k.common.program.SingleProgramImpl
import io.github.kshulzh.led4k.common.schedule.DurationSchedule
import io.github.kshulzh.led4k.common.schedule.WeekSchedule
import io.github.kshulzh.led4k.hd.fullcolor.model.HDFramePlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDPhotoPlugin
import io.github.kshulzh.led4k.hd.fullcolor.model.HDVideoPlugin
import kotlinx.io.Buffer
import kotlinx.io.files.Path
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ProgramTransformerTest {

    private fun transformer() = ProgramTransformer(
        path = Path("build/tmp/program-transformer-test"),
        width = 128,
        height = 128,
        model = "C16"
    )

    private fun imageMedia(name: String = "photo.png"): BufferMedia =
        BufferMedia(Buffer().apply { write(byteArrayOf(1, 2, 3)) }).also { it.name = name }

    private fun videoMedia(name: String = "video.mp4"): BufferMedia =
        BufferMedia(Buffer().apply { write(byteArrayOf(4, 5, 6)) }).also { it.name = name }

    // --- process(SingleProgram) structural tests ---

    @Test
    fun processSingleProgram_withImageArea_returnsSceneWithPhotoPlugin() {
        val area = ImageAreaImpl(x = 10, y = 20, width = 64, height = 32).also { it.media = imageMedia() }
        val program = SingleProgramImpl(areas = mutableListOf(area))

        val scene = transformer().process(program)

        assertEquals(1, scene.nodes.size)
        val frame = assertIs<HDFramePlugin>(scene.nodes[0])
        assertEquals(10, frame.x)
        assertEquals(20, frame.y)
        assertEquals(64, frame.width)
        assertEquals(32, frame.height)
        assertEquals(1, frame.nodes.size)
        assertIs<HDPhotoPlugin>(frame.nodes[0])
    }

    @Test
    fun processSingleProgram_withVideoArea_returnsSceneWithVideoPlugin() {
        val area = VideoAreaImpl(x = 5, y = 15, width = 100, height = 80).also { it.media = videoMedia() }
        val program = SingleProgramImpl(areas = mutableListOf(area))

        val scene = transformer().process(program)

        assertEquals(1, scene.nodes.size)
        val frame = assertIs<HDFramePlugin>(scene.nodes[0])
        assertEquals(5, frame.x)
        assertEquals(15, frame.y)
        assertEquals(100, frame.width)
        assertEquals(80, frame.height)
        assertEquals(1, frame.nodes.size)
        assertIs<HDVideoPlugin>(frame.nodes[0])
    }

    @Test
    fun processSingleProgram_withMultipleAreas_returnsFramePerArea() {
        val image = ImageAreaImpl().also { it.media = imageMedia("a.png") }
        val video = VideoAreaImpl().also { it.media = videoMedia("b.mp4") }
        val program = SingleProgramImpl(areas = mutableListOf(image, video))

        val scene = transformer().process(program)

        assertEquals(2, scene.nodes.size)
        assertIs<HDPhotoPlugin>((scene.nodes[0] as HDFramePlugin).nodes[0])
        assertIs<HDVideoPlugin>((scene.nodes[1] as HDFramePlugin).nodes[0])
    }

    // --- play mode tests ---

    @Test
    fun processSingleProgram_withFixedTimesPlayMode_setsLoopTimeMode() {
        val program = SingleProgramImpl(
            areas = mutableListOf(ImageAreaImpl().also { it.media = imageMedia() }),
            playMode = FixedTimesPlayMode(times = 5)
        )

        val scene = transformer().process(program)

        assertEquals("LoopTime", scene.playMode)
        assertEquals(5, scene.playTimes)
    }

    @Test
    fun processSingleProgram_withFixedDurationPlayMode_setsFixedTimeMode() {
        val program = SingleProgramImpl(
            areas = mutableListOf(ImageAreaImpl().also { it.media = imageMedia() }),
            playMode = FixedDurationPlayMode(ms = 15_000L)
        )

        val scene = transformer().process(program)

        assertEquals("FixedTime", scene.playMode)
        assertEquals(15, scene.playeTime)
    }

    // --- schedule tests ---

    @Test
    fun processSingleProgram_withWeekSchedule_setsWeekdayFlags() {
        val program = SingleProgramImpl(
            areas = mutableListOf(ImageAreaImpl().also { it.media = imageMedia() }),
            schedule = WeekSchedule(
                monday = true, tuesday = false, wednesday = true,
                thursday = false, friday = true, saturday = false, sunday = true
            )
        )

        val scene = transformer().process(program)

        assertEquals(1, scene.monday)
        assertEquals(0, scene.tuesday)
        assertEquals(1, scene.wednesday)
        assertEquals(0, scene.thursday)
        assertEquals(1, scene.friday)
        assertEquals(0, scene.saturday)
        assertEquals(1, scene.sunday)
    }

    @Test
    fun processSingleProgram_withDurationSchedule_setsStartAndStopTime() {
        val program = SingleProgramImpl(
            areas = mutableListOf(ImageAreaImpl().also { it.media = imageMedia() }),
            schedule = DurationSchedule(start = 0L, end = 24 * 60 * 60 * 1000L - 1)
        )

        val scene = transformer().process(program)

        assertTrue(scene.spaceStartTime.isNotEmpty())
        assertTrue(scene.spaceStopTime.isNotEmpty())
    }

    @Test
    fun processProgram_listProgram_processesAllSinglePrograms() {
        val p1 = SingleProgramImpl(areas = mutableListOf(ImageAreaImpl().also { it.media = imageMedia("a.png") }))
        val p2 = SingleProgramImpl(areas = mutableListOf(VideoAreaImpl().also { it.media = videoMedia("b.mp4") }))
        val list = ListProgramImpl(programs = mutableListOf(p1, p2))
        val t = transformer()

        t.process(list)

        assertNotNull(t.main)
        assertEquals(2, t.files.size)
    }

    // --- put() tests ---

    @Test
    fun put_imageMedia_registersInFilesMapWithCorrectExtension() {
        val media = imageMedia("myimage.png")
        val t = transformer()

        val item = t.put(media, "Photo")

        assertTrue(t.files.containsKey(item.md5))
        assertTrue(item.fileName.endsWith(".png"))
        assertEquals("Photo", item.fileKey)
        assertEquals(item.md5, item.fileName.substringBefore('.'))
    }

    @Test
    fun put_videoMedia_registersInFilesMapWithCorrectExtension() {
        val media = videoMedia("myvideo.mp4")
        val t = transformer()

        val item = t.put(media, "Video")

        assertTrue(t.files.containsKey(item.md5))
        assertTrue(item.fileName.endsWith(".mp4"))
        assertEquals("Video", item.fileKey)
    }

    @Test
    fun put_sameMedia_returnsSameMd5() {
        val bytes = byteArrayOf(10, 20, 30)
        val media1 = BufferMedia(Buffer().apply { write(bytes) }).also { it.name = "file.png" }
        val media2 = BufferMedia(Buffer().apply { write(bytes) }).also { it.name = "file.png" }
        val t = transformer()

        val item1 = t.put(media1, "Photo")
        val item2 = t.put(media2, "Photo")

        assertEquals(item1.md5, item2.md5)
    }

    @Test
    fun processImageArea_fileListContainsRegisteredFile() {
        val media = imageMedia("img.png")
        val area = ImageAreaImpl().also { it.media = media }
        val t = transformer()

        val image = t.processImageArea(area as ImageArea)
        assertIs<HDPhotoPlugin>(image)

        assertEquals(1, image.fileList.size)
        val fileItem = image.fileList[0]
        assertTrue(fileItem.fileName.endsWith(".png"))
        assertEquals("Photo", fileItem.fileKey)
    }

    @Test
    fun processVideoArea_fileListContainsRegisteredFile() {
        val media = videoMedia("clip.mp4")
        val area = VideoAreaImpl().also { it.media = media }
        val t = transformer()

        val video = t.processVideoArea(area as VideoArea)
        assertIs<HDVideoPlugin>(video)
        assertEquals(1, video.fileList.size)
        val fileItem = video.fileList[0]
        assertTrue(fileItem.fileName.endsWith(".mp4"))
        assertEquals("Video", fileItem.fileKey)
    }
}
