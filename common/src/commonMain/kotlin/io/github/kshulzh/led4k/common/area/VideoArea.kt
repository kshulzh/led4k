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
 * Represents an area specifically designed for managing and displaying video-based media.
 *
 * This interface extends the `MediaArea` interface, inheriting its properties and behavior
 * related to handling media content. Implementations of this interface are expected to provide
 * functionality for video playback, rendering, and other video-specific operations within a
 * defined spatial boundary. It serves as a more specialized abstraction for video media
 * management within a system.
 */
interface VideoArea : MediaArea