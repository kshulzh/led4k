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


/**
 * Represents a program that consists of a list of other programs.
 * Allows grouping multiple `Program` instances into a single composite program.
 */
interface ListProgram : Program {
    /**
     * Represents a mutable list of programs associated with an instance of `ListProgram`.
     *
     * This property holds a collection of `Program` instances that can be manipulated
     * to define or modify composite program structures. It is commonly used when handling
     * a group of programs in the application, such as when processing or transforming
     * program configurations.
     *
     * This property is particularly relevant for distinguishing between individual programs
     * (`SingleProgram`) and collections of programs (`ListProgram`), providing the ability
     * to operate on hierarchical or grouped program constructs.
     */
    var programs: MutableList<Program>
}