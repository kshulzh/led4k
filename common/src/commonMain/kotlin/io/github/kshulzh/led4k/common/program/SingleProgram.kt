/*
 * Copyright (c) 2024-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.common.program

import io.github.kshulzh.led4k.common.area.AreaContainer

/**
 * Represents a program that focuses on a single defined behavior or area configuration.
 *
 * This interface combines the capabilities of a `Program` and an `AreaContainer`,
 * allowing implementations to define specific functionalities for managing a single
 * program instance with associated area groupings.
 *
 * Implementations of `SingleProgram` may be used in contexts where precise control
 * of a single program's logic or configuration is required, typically in scenarios
 * involving spatial organization or localized program execution.
 */
interface SingleProgram : Program, AreaContainer