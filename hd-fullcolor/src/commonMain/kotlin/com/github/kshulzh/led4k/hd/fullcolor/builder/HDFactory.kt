/*
 * Copyright (c) 2024-2025. Kirill Shulzhenko
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

package com.github.kshulzh.led4k.hd.fullcolor.builder

import com.github.kshulzh.led4k.common.area.BaseAreaContainer
import com.github.kshulzh.led4k.common.area.BaseImageArea
import com.github.kshulzh.led4k.common.area.BaseVideoArea
import com.github.kshulzh.led4k.common.builder.Factory
import com.github.kshulzh.led4k.common.program.BaseSingleProgram
import com.github.kshulzh.led4k.common.program.ListProgram
import com.github.kshulzh.led4k.common.program.SingleProgram
import com.github.kshulzh.led4k.hd.fullcolor.area.HDAreaContainer
import com.github.kshulzh.led4k.hd.fullcolor.area.HDImageArea
import com.github.kshulzh.led4k.hd.fullcolor.area.HDVideoArea
import com.github.kshulzh.led4k.hd.fullcolor.program.HDListProgram
import com.github.kshulzh.led4k.hd.fullcolor.program.HDSingleProgram
import kotlin.reflect.KClass


class HDFactory : Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> create(klass: KClass<T>): T {
        return when (klass) {
            ListProgram::class -> HDListProgram()
            BaseImageArea::class -> HDImageArea()
            BaseVideoArea::class -> HDVideoArea()
            BaseSingleProgram::class, SingleProgram::class -> HDSingleProgram()
            BaseAreaContainer::class -> HDAreaContainer()
            else -> throw RuntimeException("Unsupported class $klass")
        } as T
    }
}