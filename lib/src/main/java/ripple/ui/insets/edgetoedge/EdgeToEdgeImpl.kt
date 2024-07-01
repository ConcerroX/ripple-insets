package ripple.ui.insets.edgetoedge

import android.view.View
import android.view.Window
import androidx.annotation.ColorInt
import ripple.ui.insets.SystemBarStyleScheme


internal interface EdgeToEdgeImpl {

    fun setUp(
        window: Window,
        view: View,
        statusBarStyleScheme: SystemBarStyleScheme,
        navigationBarStyleScheme: SystemBarStyleScheme,
        @ColorInt statusBarScrimColor: Int,
        @ColorInt navigationBarScrimColor: Int,
        statusBarIsDark: Boolean,
        navigationBarIsDark: Boolean
    )
}