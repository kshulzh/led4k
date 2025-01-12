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

import com.github.kshulzh.led4k.common.builder.Builder
import com.github.kshulzh.led4k.common.builder.BuilderContext
import kotlin.reflect.KClass

class ProgramBuilder<E : Program, C : BuilderContext>(
    override var klass: KClass<E>,
    val builder: context(C) E.() -> Unit
) : Builder<E, C>, Program {
    override fun build(context: C): E {
        return super.build(context).also {
            builder(context, it)
        }
    }
}

//inline fun <reified E : Program, C : BuilderContext> program(noinline builder: context(C) E.() -> Unit): Program {
//    return ProgramBuilder<E, C>(E::class, builder)
//}

inline fun <reified E : Program> program(noinline builder: context(BuilderContext) E.() -> Unit): Program {
    return ProgramBuilder(E::class, builder)
}

context(BuilderContext)
inline fun <reified E : Program> program(noinline builder: context(BuilderContext) E.() -> Unit): Program {
    return ProgramBuilder<E, BuilderContext>(E::class, builder).build(this@BuilderContext)
}

