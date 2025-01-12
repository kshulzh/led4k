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

package io.github.kshulzh.led4k.builder

import io.github.kshulzh.led4k.common.area.ImageArea
import io.github.kshulzh.led4k.common.area.VideoArea
import io.github.kshulzh.led4k.common.builder.image
import io.github.kshulzh.led4k.common.builder.location
import io.github.kshulzh.led4k.common.builder.program
import io.github.kshulzh.led4k.common.builder.size
import io.github.kshulzh.led4k.common.builder.video
import io.github.kshulzh.led4k.common.media.PathMedia
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class BuilderTest {
    @Test
    fun shouldBuildProgram() {
        val program = program {
            video {
                media = PathMedia("97f35e08d40b46e5b6ad630cfd9f31f5.mp4")
                size(128,160)
                location(0,0)
            }
            image {
                media = PathMedia("c836fc71329b9708d9bd1ab1f950ad3f.png")
                size(128,128)
                location(0,0)
            }
            image {
                media = PathMedia("d741d890914011019ba65f874662bbc1.png")
                size(128,128)
                location(0,32)
            }
        }

        assertEquals(3, program.areas.size)
        assertIs<VideoArea>(program.areas[0])
        assertIs<ImageArea>(program.areas[1])
        assertIs<ImageArea>(program.areas[2])
    }
}