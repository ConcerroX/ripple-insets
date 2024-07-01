package ripple.ui.insets

import android.graphics.Color

internal const val DefaultLightScrimColor = 0xE6FFFFFF.toInt()
internal const val DefaultDarkScrimColor = 0x801B1B1B.toInt()

internal const val DefaultScrimColor = DefaultDarkScrimColor

internal val DefaultStatusBarStyleScheme = SystemBarStyleScheme(
    lightStyle = SystemBarStyle(Color.TRANSPARENT, true),
    darkStyle = SystemBarStyle(Color.TRANSPARENT, true)
)
internal val DefaultNavigationBarStyleScheme = SystemBarStyleScheme(
    lightStyle = SystemBarStyle(Color.TRANSPARENT, false),
    darkStyle = SystemBarStyle(Color.TRANSPARENT, true)
)

class SystemBarStyleScheme(
    internal val lightStyle: SystemBarStyle,
    internal val darkStyle: SystemBarStyle,
) {
    fun getScrimColor(isDark: Boolean) = if (isDark) darkStyle.scrimColor else lightStyle.scrimColor

    fun getSystemBarDarkMode(isDark: Boolean) =
        if (isDark) darkStyle.systemBarDarkMode else lightStyle.systemBarDarkMode
}