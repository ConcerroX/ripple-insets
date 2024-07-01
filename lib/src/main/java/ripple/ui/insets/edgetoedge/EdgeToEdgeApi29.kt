package ripple.ui.insets.edgetoedge

import android.os.Build
import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.DoNotInline
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import ripple.ui.insets.SystemBarStyleScheme

internal class EdgeToEdgeApi29 : EdgeToEdgeImpl {
    @RequiresApi(Build.VERSION_CODES.Q)
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
        window.statusBarColor = statusBarScrimColor
        window.navigationBarColor = navigationBarScrimColor
        window.isStatusBarContrastEnforced = false
        window.isNavigationBarContrastEnforced = false
        WindowInsetsControllerCompat(window, view).run {
            isAppearanceLightStatusBars = !statusBarIsDark
            isAppearanceLightNavigationBars = !navigationBarIsDark
        }
    }
}