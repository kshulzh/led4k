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

package io.github.kshulzh.led4k.common.media

/**
 * Represents the name of the media.
 *
 * This property provides access to the "name" attribute of a media object. It is stored
 * within the `led4kAttributes` map and can be retrieved or updated dynamically. The name
 * typically describes or identifies the media in a human-readable format, which can be
 * useful for categorization or display purposes.
 *
 * The property is nullable, indicating that a media object may not always have an associated name.
 */
var Media.name: String?
    get() = led4kAttributes["name"] as? String
    set(value) {
        led4kAttributes["name"] = value
    }