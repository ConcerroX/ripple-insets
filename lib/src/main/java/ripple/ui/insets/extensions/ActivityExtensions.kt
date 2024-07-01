package ripple.ui.insets.extensions

import android.app.Activity
import ripple.ui.insets.DefaultNavigationBarStyleScheme
import ripple.ui.insets.DefaultStatusBarStyleScheme
import ripple.ui.insets.edgetoedge.EdgeToEdge
import ripple.ui.insets.SystemBarStyleScheme

fun Activity.enableEdgeToEdge(
    statusBarStyleScheme: SystemBarStyleScheme = DefaultStatusBarStyleScheme,
    navigationBarStyleScheme: SystemBarStyleScheme = DefaultNavigationBarStyleScheme
) {
    EdgeToEdge.enable(this, statusBarStyleScheme, navigationBarStyleScheme)
}