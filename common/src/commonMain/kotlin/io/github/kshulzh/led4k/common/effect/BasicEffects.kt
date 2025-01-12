/*
 * Copyright (c) 2025. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.common.effect

/**
 * Represents a set of basic visual effects that implement the Effect interface.
 */
enum class BasicEffects : Effect {
    /**
     * Represents the default effect with no special behaviors applied.
     */
    DEFAULT,
    /**
     * Represents an effect that immediately displays content on the target without delay.
     * This effect is often used in scenarios where instant visual feedback is required.
     */
    IMMEDIATE_SHOW,
    /**
     * Represents an effect where the screen content is preserved and not cleared.
     * This effect allows for rendering updates or changes without resetting the display.
     */
    DONT_CLEAR_SCREEN,
    /**
     * Represents an effect that forces an immediate clearing of the screen's contents.
     *
     * This effect can be used when the current display state needs to be reset immediately,
     * such as in scenarios where no residual content should remain visible before proceeding
     * with new visual updates.
     */
    IMMEDIATE_CLEAR
}