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

package io.github.kshulzh.led4k.common.controller

import io.github.kshulzh.led4k.common.program.Program

/**
 * Represents a controller responsible for managing and processing `Program` instances.
 * It acts as a high-level interface for interactions that involve the transmission
 * or execution of `Program` objects, which can encapsulate various configurations
 * and operations.
 *
 * Implementations of this interface are expected to provide concrete mechanisms for
 * program handling, potentially involving communication or data transfer tasks.
 */
interface Controller {
    /**
     * Uploads the given program to the appropriate target.
     *
     * @param program The program to be uploaded. It can be a single program
     * or a list of programs, depending on the implementation and the context.
     * Programs may include configurations, schedules, or area definitions.
     */
    suspend fun upload(program: Program)
}