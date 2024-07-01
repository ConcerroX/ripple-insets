package ripple.ui.insets.extensions

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import ripple.ui.insets.DefaultNavigationBarStyleScheme
import ripple.ui.insets.DefaultStatusBarStyleScheme
import ripple.ui.insets.edgetoedge.EdgeToEdge
import ripple.ui.insets.SystemBarStyleScheme

@JvmOverloads
fun Activity.enableEdgeToEdge(
    statusBarStyleScheme: SystemBarStyleScheme = DefaultStatusBarStyleScheme,
    navigationBarStyleScheme: SystemBarStyleScheme = DefaultNavigationBarStyleScheme,
    detectDarkMode: (Resources) -> Boolean = { resources ->
        (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
    },
) {
    EdgeToEdge.enable(this, statusBarStyleScheme, navigationBarStyleScheme, detectDarkMode)
}