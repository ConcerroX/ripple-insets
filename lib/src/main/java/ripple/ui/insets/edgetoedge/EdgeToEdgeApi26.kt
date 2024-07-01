package ripple.ui.insets.edgetoedge

import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.DoNotInline
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import ripple.ui.insets.SystemBarStyleScheme

internal class EdgeToEdgeApi26 : EdgeToEdgeImpl {
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
        WindowInsetsControllerCompat(window, view).run {
            isAppearanceLightStatusBars = !statusBarIsDark
            isAppearanceLightNavigationBars = !navigationBarIsDark
        }
    }
}