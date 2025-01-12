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

import io.github.kshulzh.led4k.common.effect.ContinuousEffect
import io.github.kshulzh.led4k.common.effect.Effect


/**
 * Interface representing a continuous high-definition effect configuration.
 * It extends the `HDEffect` interface and introduces additional properties
 * or behavior specific to continuous effects.
 */
interface HDEffectContinue : HDEffect {
    /**
     * Indicates whether the head of a continuous effect is close to the tail.
     *
     * This value is represented as an integer, where:
     * - `1` signifies that the head is close to the tail.
     * - `0` signifies that the head is not close to the tail.
     *
     * It is typically set in the context of a continuous effect to reflect
     * the behavior or characteristics of the effect being applied.
     */
    var headCloseToTail: Int
    /**
     * Configures and applies a new effect, updating the display effect, clear effect,
     * and associated timing properties based on the type of effect provided.
     *
     * @param e The effect to be set. This can be one of the following types:
     *          - `ContinuousEffect`: Updates `dispTime`, `dispEffect`, `headCloseToTail`,
     *            `holdTime`, `clearEffect`, and `clearTime` based on the effect's properties.
     *          - `ContinuousEffect.ContinuousEffects`: Updates `dispEffect`, `holdTime`,
     *            `clearEffect`, and `clearTime` based on the effect's properties.
     */
    override fun setEffect(e: Effect) {
        super.setEffect(e)
        if (e is ContinuousEffect) {
            dispTime = e.speed.toInt()
            dispEffect = e.continuousEffect.hdCode
            headCloseToTail = if(e.headCloseToTail) 1 else 0
            holdTime = 50
            clearEffect = 25
            clearTime = 5
        } else if (e is ContinuousEffect.ContinuousEffects) {
            dispEffect = e.hdCode
            holdTime = 50
            clearEffect = 25
            clearTime = 5
        }
    }
}