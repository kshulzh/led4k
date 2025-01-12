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
 * Represents a continuous effect that can be applied to a visual display,
 * allowing for dynamic animations or movements in a specified direction.
 *
 * @property continuousEffect The type of continuous effect to be applied, defined by the ContinuousEffects enum.
 * @property speed The speed at which the effect is applied, with a default value of 5.0.
 * @property headCloseToTail A flag indicating whether the head of the effect should be rendered
 * close to its tail, creating a looping visual effect.
 */
class ContinuousEffect(
    val continuousEffect: ContinuousEffects,
    val speed: Double = 5.0,
    val headCloseToTail: Boolean = false
) : Effect {
    /**
     * Defines a set of continuous movement effects that can be applied.
     *
     * These effects represent directional movement behaviors and are typically associated
     * with animations or visual transitions where the target moves in a continuous manner.
     *
     * Each effect specifies one of the four cardinal movement directions:
     * upward, downward, leftward, or rightward.
     */
    enum class ContinuousEffects : Effect {
        /**
         * Represents a continuous upward movement effect.
         *
         * It is part of the ContinuousEffects enumeration and defines a behavior
         * where movement is applied consistently in the upward direction.
         */
        CONTINUOUS_MOVE_UP,
        /**
         * Represents a continuous movement effect in the downward direction.
         * This effect is typically applied to create an animation where content
         * or objects appear to move continuously from top to bottom.
         */
        CONTINUOUS_MOVE_DOWN,
        /**
         * Represents a continuous movement effect in the leftward direction.
         * This effect is typically used in visual systems where elements move
         * consistently to the left, providing a dynamic animation or transition.
         */
        CONTINUOUS_MOVE_LEFT,
        /**
         * Represents a continuous rightward movement effect.
         *
         * This effect is part of the `ContinuousEffects` enumeration and is used to apply
         * a visual effect where the content moves continuously to the right.
         */
        CONTINUOUS_MOVE_RIGHT
    }
}