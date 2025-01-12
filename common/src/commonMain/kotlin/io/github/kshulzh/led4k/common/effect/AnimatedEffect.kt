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

package io.github.kshulzh.led4k.common.effect

/**
 * Represents an animated effect that can be applied to a display or component.
 * This effect provides a variety of animated transitions and motions.
 *
 * @property animatedEffects The type of animated effect to be applied.
 * @property speed The speed at which the animation is executed. The default value is 0.0.
 */
data class AnimatedEffect(val animatedEffects: AnimatedEffects, var speed: Double = 0.0) : Effect {
    /**
     * Represents a set of animated effects that can be applied to visual elements.
     * Each effect describes a specific animation or transition behavior.
     */
    enum class AnimatedEffects : Effect {
        /**
         * Represents a type of animated effect where the transition or display is chosen randomly
         * among available effects. This can introduce variability and unpredictability into the
         * display behavior.
         */
        RANDOM,
        /**
         * Represents an animation effect where elements move to the left direction on the display.
         *
         * This effect is a part of the AnimatedEffects enum class, which includes various types
         * of animations for rendering graphical transitions or movements on the display.
         *
         * MOVE_LEFT typically shifts the content horizontally to the left in a smooth manner,
         * creating a dynamic visual effect.
         */
        MOVE_LEFT,
        /**
         * Represents the "MOVE_RIGHT" animated effect.
         *
         * This effect applies a movement animation that shifts the content towards the right. It is often
         * used in visual screens or displays to emphasize the motion of elements shifting from left to right.
         *
         * Belongs to the `AnimatedEffects` enum which organizes multiple display-related animation effects.
         */
        MOVE_RIGHT,
        /**
         * Represents an animated effect where content moves upward
         * within a display, creating a vertical scrolling animation.
         *
         * This effect is part of the `AnimatedEffects` enumeration and
         * can be used to enhance visual presentation by shifting the
         * current display data upwards.
         */
        MOVE_UP,
        /**
         * Represents an animation effect where content moves downward.
         * This effect is part of the `AnimatedEffects` enumeration and can be used
         * to apply a downward movement animation to the display.
         */
        MOVE_DOWN,
        /**
         * Represents an animation effect where the content is covered from the left side of the display.
         *
         * This effect gradually transitions by overlaying or obscuring the content from left to right,
         * creating a covering animation starting from the left edge of the display area.
         *
         * Commonly used in scenarios where a left-to-right covering animation is needed,
         * such as transition effects in visual displays or LED panels.
         */
        COVER_LEFT,
        /**
         * Represents an animation effect where content is progressively revealed from the right side of the display.
         */
        COVER_RIGHT,
        /**
         * Represents an animated effect where content is progressively covered from the bottom to the top.
         */
        COVER_UP,
        /**
         * Represents an animation effect where content progressively covers the display from top to bottom.
         *
         * This effect creates a downward covering motion, gradually revealing the content in a smooth animation.
         */
        COVER_DOWN,
        /**
         * Represents an animated effect where the display is progressively covered from the top-left corner.
         * The effect starts at the top-left corner and expands outward until the display is fully covered.
         *
         * This effect is part of the AnimatedEffects enum, which defines various visual transitions and animations
         * that can be applied to an LED panel or similar display device.
         */
        TOP_LEFT_COVER,
        /**
         * Represents an animation effect originating from the bottom left corner,
         * characterized by a gradual covering transition. This effect provides
         * a visually appealing transition starting from the bottom left and is
         * primarily used within animated sequences to create dynamic visuals.
         */
        BOTTOM_LEFT_COVERT,
        /**
         * Represents an animation effect where the display content is gradually covered
         * from the top-right corner to the opposite corner.
         *
         * This effect belongs to the `AnimatedEffects` category and can be used
         * as part of a sequence of visual animations. It provides a smooth transition
         * that emphasizes the covering action originating from the top-right corner.
         */
        TOP_RIGHT_COVER,
        /**
         * Represents the "BOTTOM_RIGHT_COVERT" animation effect where the display transitions by covering
         * the content starting from the bottom-right corner.
         */
        BOTTOM_RIGHT_COVERT,
        /**
         * Represents an animated effect where content opens from the middle outward.
         * Typically used to create a visual transition effect in animations.
         */
        OPEN_FROM_MIDDLE,
        /**
         * Represents an animation effect where the display contents open vertically
         * outward in both upward and downward directions.
         *
         * This effect is commonly used in animated transitions to create a visually
         * engaging reveal from the center of the display toward the top and bottom.
         */
        UP_DOWN_OPEN,
        /**
         * Represents an animation effect where the content closes inwards from the middle of the display.
         * This effect creates a visual impression of content converging towards the center of the screen.
         */
        CLOSE_FROM_MIDDLE,
        /**
         * Represents an animated effect where the display closes vertically
         * with sections moving in an up-and-down motion.
         *
         * This effect is part of the `AnimatedEffects` enumeration and is
         * typically used to create a closure animation that splits the display
         * into alternating sections moving up and down toward the middle.
         */
        UP_DOWN_CLOSE,
        /**
         * Represents a gradual change effect in visual animations.
         * This effect involves smooth transitions between states or visuals.
         */
        GRADUAL_CHANGE,
        /**
         * An animation effect that simulates a vertical blinds transition.
         *
         * This effect divides the display area into vertical strips and gradually reveals
         * or hides the content strip by strip, creating a visual effect resembling the opening
         * or closing of vertical blinds.
         *
         * Commonly used to achieve a visually engaging transition in LED displays or
         * screen-based animations.
         */
        VERTICAL_BLINDS,
        /**
         * Represents a horizontal blinds effect where the display transitions
         * with horizontal slats opening or closing in a sequential manner.
         * This effect can be used to create a visually engaging transition.
         */
        HORIZONTAL_BLINDS,
        /**
         * Represents the TWINKLE effect used to create a visual appearance of lights flickering
         * intermittently in a pattern resembling stars or sparkles.
         *
         * This effect is part of the `AnimatedEffects` enum and can be utilized to animate visuals
         * in various LED displays. Typically applied to project a decorative and dynamic lighting style.
         */
        TWINKLE
    }
}