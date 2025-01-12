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

package com.github.kshulzh.led4k.common.program

import com.github.kshulzh.led4k.common.builder.BuilderContext
import com.github.kshulzh.led4k.common.builder.obj


interface ListProgram : Program {
    var programs: MutableList<Program>
    fun <T : Program> addProgram(e: T): T {
        return e.also { programs.add(e) }
    }
}

//context(C)
//inline fun <reified T : Program, C : BuilderContext> ListProgram.program(noinline builder: context(C) T.() -> Unit): T {
//    return addProgram(obj<T, C>(builder))
//}

context(BuilderContext)
inline fun <reified T : Program> ListProgram.program(noinline builder: context(BuilderContext) T.() -> Unit): T {
    return addProgram(obj<T, BuilderContext>(builder))
}