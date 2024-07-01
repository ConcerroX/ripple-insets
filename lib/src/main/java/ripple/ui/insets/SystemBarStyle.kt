package ripple.ui.insets

import android.app.UiModeManager
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import androidx.annotation.ColorInt

class SystemBarStyle constructor(
    @ColorInt internal val scrimColor: Int = Color.TRANSPARENT,
    internal val systemBarDarkMode: Boolean,
) {

}

//class SystemBarStyle private constructor(
//    internal val lightScrim: Int,
//    internal val darkScrim: Int,
//    internal val nightMode: Int,
//    internal val detectDarkMode: (Resources) -> Boolean,
//) {
//
//    internal lateinit var detectBarDarkMode: (Resources) -> Boolean
//
//    companion object {
//
//        private fun detectBarDarkModeFromScrim(
//            style: SystemBarStyle, resources: Resources
//        ): Boolean {
//            return false
//        }
//
//        /**
//         * Creates a new instance of [SystemBarStyle]. This style detects the dark mode
//         * automatically.
//         * - On API level 29 and above, the bar will be transparent in the gesture navigation mode.
//         *   If this is used for the navigation bar, it will have the scrim automatically applied
//         *   by the system in the 3-button navigation mode. _Note that neither of the specified
//         *   colors are used_. If you really want a custom color on these API levels, use [dark] or
//         *   [light].
//         * - On API level 28 and below, the bar will be one of the specified scrim colors depending
//         *   on the dark mode.
//         * @param lightScrim The scrim color to be used for the background when the app is in light
//         * mode.
//         * @param darkScrim The scrim color to be used for the background when the app is in dark
//         * mode. This is also used on devices where the system icon color is always light.
//         * @param detectDarkMode Optional. Detects whether UI currently uses dark mode or not. The
//         * default implementation can detect any of the standard dark mode features from the
//         * platform, appcompat, and Jetpack Compose.
//         */
//        @JvmStatic
//        @JvmOverloads
//        fun auto(
//            @ColorInt lightScrim: Int,
//            @ColorInt darkScrim: Int,
//            detectDarkMode: (Resources) -> Boolean = { resources ->
//                (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
//            },
//            detectBarDarkMode: (SystemBarStyle, Resources) -> Boolean = this::detectBarDarkModeFromScrim
//        ): SystemBarStyle {
//            return SystemBarStyle(
//                lightScrim = lightScrim,
//                darkScrim = darkScrim,
//                nightMode = UiModeManager.MODE_NIGHT_AUTO,
//                detectDarkMode = detectDarkMode,
//            ).apply {
//                this.detectBarDarkMode = { resources -> detectBarDarkMode(this, resources) }
//            }
//        }
//
//        /**
//         * Creates a new instance of [SystemBarStyle]. This style consistently applies the specified
//         * scrim color regardless of the system navigation mode.
//         *
//         * @param scrim The scrim color to be used for the background. It is expected to be dark
//         * for the contrast against the light system icons.
//         */
//        @JvmStatic
//        fun dark(@ColorInt scrim: Int): SystemBarStyle {
//            return SystemBarStyle(
//                lightScrim = scrim,
//                darkScrim = scrim,
//                nightMode = UiModeManager.MODE_NIGHT_YES,
//                detectDarkMode = { _ -> true },
//            ).apply {
//                detectBarDarkMode =
//                    { resources -> detectBarDarkModeFromScrim(this, resources) }
//            }
//        }
//
//        /**
//         * Creates a new instance of [SystemBarStyle]. This style consistently applies the specified
//         * scrim color regardless of the system navigation mode.
//         *
//         * @param scrim The scrim color to be used for the background. It is expected to be light
//         * for the contrast against the dark system icons.
//         * @param darkScrim The scrim color to be used for the background on devices where the
//         * system icon color is always light. It is expected to be dark.
//         */
//        @JvmStatic
//        fun light(@ColorInt scrim: Int, @ColorInt darkScrim: Int): SystemBarStyle {
//            return SystemBarStyle(
//                lightScrim = scrim,
//                darkScrim = darkScrim,
//                nightMode = UiModeManager.MODE_NIGHT_NO,
//                detectDarkMode = { _ -> false },
//            ).apply {
//                detectBarDarkMode =
//                    { resources -> detectBarDarkModeFromScrim(this, resources) }
//            }
//        }
//    }
//
//    internal fun getScrim(isDark: Boolean) = if (isDark) darkScrim else lightScrim
//
//    internal fun getScrimWithEnforcedContrast(isDark: Boolean): Int {
//        return when {
//            nightMode == UiModeManager.MODE_NIGHT_AUTO -> Color.TRANSPARENT
//            isDark -> darkScrim
//            else -> lightScrim
//        }
//    }
//}