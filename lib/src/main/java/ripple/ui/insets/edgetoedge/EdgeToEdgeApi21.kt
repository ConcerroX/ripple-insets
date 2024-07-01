package ripple.ui.insets.edgetoedge

import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import androidx.annotation.DoNotInline
import androidx.core.view.WindowCompat
import ripple.ui.insets.DefaultScrimColor
import ripple.ui.insets.SystemBarStyleScheme

internal class EdgeToEdgeApi21 : EdgeToEdgeImpl {
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
        window.statusBarColor = if (statusBarIsDark) statusBarScrimColor else DefaultScrimColor
        window.navigationBarColor =
            if (navigationBarIsDark || isEmui) navigationBarScrimColor else DefaultScrimColor
    }
}