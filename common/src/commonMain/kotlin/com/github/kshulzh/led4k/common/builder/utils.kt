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

package com.github.kshulzh.led4k.common.builder

context(C)
inline fun <reified E : Any, C : BuilderContext> obj(noinline builder: context(C) E.() -> Unit): E {
    return builder(builder).build(this@C)
}

inline fun <reified E : Any, C : BuilderContext> builder(noinline builder: context(C) E.() -> Unit): Builder<E, C> {
    return BuilderImpl(E::class, builder)
}