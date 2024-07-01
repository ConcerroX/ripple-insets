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
import java.io.BufferedReader
import java.io.InputStreamReader

internal class EdgeToEdgeMiuiApi21 : EdgeToEdgeImpl {

    companion object {
        fun isAvailable(): Boolean {
            if (!Build.MANUFACTURER.equals("Xiaomi", true)) return false
            BufferedReader(
                InputStreamReader(
                    Runtime.getRuntime().exec("getprop ro.miui.ui.version.name").inputStream
                ), 1024
            ).use {
                return readlnOrNull() == "V6"
            }
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

        // MIUI 6 has implemented its own light status bar.
        try {
            val attributesClass = Class.forName("android.view.MiuiWindowManager\$LayoutParams")
            fun getExtraFlag(name: String) =
                attributesClass.getField(name).apply { isAccessible = true }.getInt(attributesClass)

            val statusBarDarkModeFlag = getExtraFlag("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
            val intType = Int::class.javaPrimitiveType
            val setExtraFlagsMethod =
                attributesClass.getMethod("setExtraFlags", intType, intType).apply {
                    isAccessible = true
                }
            if (statusBarIsDark) {
                setExtraFlagsMethod.invoke(window, 0, statusBarDarkModeFlag)
            } else {
                setExtraFlagsMethod.invoke(window, statusBarDarkModeFlag, statusBarDarkModeFlag)
            }
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