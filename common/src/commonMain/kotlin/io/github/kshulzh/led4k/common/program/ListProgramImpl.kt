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

package io.github.kshulzh.led4k.common.program

/**
 * Implementation of the `ListProgram` interface, representing a program
 * that organizes a collection of other programs.
 *
 * This class provides functionality to manage and modify a composite program
 * structure, allowing the grouping of multiple `Program` instances.
 *
 * @property programs A mutable list of `Program` instances associated with
 * this `ListProgramImpl`. This property facilitates the handling of individual
 * or grouped programs in a modular and configurable manner.
 */
class ListProgramImpl(override var programs: MutableList<Program> = mutableListOf()) : ListProgram