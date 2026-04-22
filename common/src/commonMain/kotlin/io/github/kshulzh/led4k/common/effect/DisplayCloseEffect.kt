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
 * Represents a composite effect that combines an initial display effect,
 * a holding duration, and a clearing effect. This class is useful for
 * orchestrating the visual appearance, a duration of persistence, and the
 * eventual disappearance of content on a display.
 *
 * @property displayEffect The effect to be applied when content is initially displayed.
 * @property hold The duration (in seconds) for which the content remains visible
 * before the clearing effect is applied. Defaults to 5.0 seconds.
 * @property clearEffect The effect to be applied when clearing the content
 * from the display.
 */
class DisplayCloseEffect(
    val displayEffect: Effect = BasicEffects.DEFAULT,
    val hold: Double = 5.0,
    val clearEffect: Effect = BasicEffects.DEFAULT
) : Effect