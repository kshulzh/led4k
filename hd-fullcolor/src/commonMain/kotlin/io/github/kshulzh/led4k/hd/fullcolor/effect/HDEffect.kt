/*
 * Copyright (c) 2025-2026. Kirill Shulzhenko
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

package io.github.kshulzh.led4k.hd.fullcolor.effect

import io.github.kshulzh.led4k.common.effect.AnimatedEffect
import io.github.kshulzh.led4k.common.effect.BasicEffects
import io.github.kshulzh.led4k.common.effect.DisplayCloseEffect
import io.github.kshulzh.led4k.common.effect.Effect

/**
 * Interface representing a high-definition effect configuration, which manages
 * the display and clearing effects, along with their associated times and parameters.
 *
 * This interface is designed to provide a flexible mechanism for applying and managing
 * a sequence of visual effects with various configurations.
 */
interface HDEffect {
    /**
     * Represents the code corresponding to the effect used to clear a screen or display.
     *
     * This variable is often assigned based on the `clearEffect` property of an effect instance,
     * which determines the animation or behavior applied during the clearing process.
     *
     * The value can be derived from different types of effects, including `BasicEffects`,
     * `AnimatedEffect`, and their subtypes. For example, the `hdCode` of a `BasicEffect` or
     * `AnimatedEffect` is used to update this variable.
     *
     * The exact numeric value assigned to this variable depends on the clear effect's type
     * and its corresponding code, indicating the desired visual outcome during the clear operation.
     */
    var clearEffect: Int
    /**
     * Specifies the duration in milliseconds to execute the screen-clear effect.
     * This property is typically set when applying an `AnimatedEffect` or `ContinuousEffect`
     * as part of the effect configuration.
     *
     * The value is calculated based on the speed of the associated effect and is used
     * to determine the timing for completing the clearing transition on the display.
     */
    var clearTime: Int
    /**
     * Represents the code of the display effect that determines how the content is shown on the screen.
     *
     * The value of this property, represented as an integer, corresponds to a specific type of effect,
     * which can include basic effects, animated effects, or continuous effects.
     * The exact effect is determined in conjunction with the effect configuration logic
     * in the `setEffect` method.
     *
     * This property is dynamically set based on the type of effect applied:
     * - For `BasicEffects`, the value corresponds to its predefined `hdCode`.
     * - For `AnimatedEffects`, the speed and specific animation type are used to derive its corresponding `hdCode`.
     * - For `ContinuousEffects`, the associated `hdCode` defines the continuous motion effect style.
     */
    var dispEffect: Int
    /**
     * Represents the duration (in units of time) during which the effect is held
     * before transitioning or completing. The value is typically calculated based
     * on input effects and is used to manage the timing of display elements.
     *
     * Modifications to this property may influence the timing behavior of the
     * visual effect and its synchronizations with other effect parameters.
     */
    var holdTime: Int
    /**
     * Represents the duration or speed at which a particular display effect
     * is rendered or transitions are applied, typically in milliseconds.
     *
     * This property is set primarily in the `setEffect` function based on the speed
     * of the specific `Effect` instance being applied. It plays a role in determining
     * how quickly the display transitions or animations execute.
     *
     * For `AnimatedEffect` or `ContinuousEffect`, this is derived directly from the
     * effect's speed property.
     */
    var dispTime: Int

    /**
     * Sets the provided effect and updates the corresponding display and clear effect properties
     * along with their durations. The method processes the input effect to determine the type
     * of display and clearing behaviors to apply.
     *
     * @param e The effect to be applied. Can be one of the following:
     *          - DisplayCloseEffect: A composite effect that includes display, hold duration, and clear effect.
     *          - AnimatedEffect: An effect with animations, translated into a DisplayCloseEffect.
     *          - BasicEffects: A basic effect, translated into a DisplayCloseEffect.
     *          - AnimatedEffect.AnimatedEffects: A specific animated sub-effect.
     */
    fun setEffect(e: Effect) {
        when (e) {
            is DisplayCloseEffect -> e
            is AnimatedEffect -> DisplayCloseEffect(e)
            is BasicEffects -> DisplayCloseEffect(e)
            is AnimatedEffect.AnimatedEffects -> DisplayCloseEffect(e)
            else -> return
        }.also {
            holdTime = it.hold.toInt() * 10
            when (val displayEffect = it.displayEffect) {
                is BasicEffects -> {
                    if (displayEffect != BasicEffects.DEFAULT) {
                        dispEffect = displayEffect.hdCode
                    }
                }

                is AnimatedEffect -> {
                    dispTime = displayEffect.speed.toInt()
                    dispEffect = displayEffect.animatedEffects.hdCode
                }

                is AnimatedEffect.AnimatedEffects -> {
                    dispEffect = displayEffect.hdCode
                }
            }
            when (val clearEffect = it.clearEffect) {
                is BasicEffects -> {
                    if (clearEffect != BasicEffects.DEFAULT) {
                        this.clearEffect = clearEffect.hdCode
                    }
                }

                is AnimatedEffect -> {
                    clearTime = clearEffect.speed.toInt()
                    this.clearEffect = clearEffect.animatedEffects.hdCode
                }

                is AnimatedEffect.AnimatedEffects -> {
                    this.clearEffect = clearEffect.hdCode
                }
            }
        }

    }
}