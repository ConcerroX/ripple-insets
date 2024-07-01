package ripple.ui.insets.edgetoedge

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.DoNotInline
import androidx.core.view.WindowCompat
import ripple.ui.insets.DefaultScrimColor
import ripple.ui.insets.SystemBarStyleScheme

internal class EdgeToEdgeFlymeOsApi21 : EdgeToEdgeImpl {

    companion object {
        fun isAvailable(): Boolean {
            if (!Build.MANUFACTURER.equals("Meizu", true)) return false
            return Build.DISPLAY.startsWith("5")
        }
    }

    @Suppress("DEPRECATION", "PrivateApi")
    @DoNotInline
    override fun setUp(
        window: Window,
        view: View,
        statusBarStyleScheme: SystemBarStyleScheme,
        navigationBarStyleScheme: SystemBarStyleScheme,
        @ColorInt statusBarScrimColor: Int,
        @ColorInt navigationBarScrimColor: Int,
        statusBarIsDark: Boolean,
        navigationBarIsDark: Boolean
    ) {
        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = statusBarScrimColor

        // FlymeOS 5 has implemented its own light status bar.
        try {
            val attributes = window.attributes
            val attributesClass = window.attributes.javaClass
            val statusBarDarkModeFlag =
                attributesClass.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON")
                    .apply { isAccessible = true }.getInt(attributesClass)
            val meizuFlagsField =
                attributesClass.getDeclaredField("meizuFlags").apply { isAccessible = true }
            val meizuFlags = meizuFlagsField.getInt(attributes)
            if (statusBarIsDark) {
                meizuFlagsField.setInt(attributes, meizuFlags and statusBarDarkModeFlag)
            } else {
                meizuFlagsField.setInt(attributes, meizuFlags or statusBarDarkModeFlag)
            }
            window.attributes = attributes
        } catch (e: Exception) {
            e.printStackTrace()
        }

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        if (navigationBarIsDark) {
            window.navigationBarColor = navigationBarScrimColor
        } else {
            window.navigationBarColor = DefaultScrimColor
        }
    }
}