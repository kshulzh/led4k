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

package io.github.kshulzh.led4k.common.area

/**
 * Represents an area specifically designed to contain and display image-based media.
 *
 * This interface extends the `MediaArea` interface, inheriting its properties and behavior
 * related to handling media, while also serving as a more specific abstraction for image
 * media management. Implementations of this interface are expected to manage image rendering
 * within a defined spatial boundary, enabling further customizations or effects specifically
 * tailored for images.
 */
interface ImageArea : MediaArea