package ripple.ui.insets.edgetoedge

import android.app.Activity
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import ripple.ui.insets.DefaultNavigationBarStyleScheme
import ripple.ui.insets.DefaultStatusBarStyleScheme
import ripple.ui.insets.SystemBarStyleScheme

//internal val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
//internal val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

internal val isEmui =
    Build.MANUFACTURER.equals("Huawei", true) || Build.MANUFACTURER.equals("Honor", true)

object EdgeToEdge {

    private var Impl: EdgeToEdgeImpl? = null

    @JvmStatic
    fun enable(
        activity: Activity,
        statusBarStyleScheme: SystemBarStyleScheme = DefaultStatusBarStyleScheme,
        navigationBarStyleScheme: SystemBarStyleScheme = DefaultNavigationBarStyleScheme,
        detectDarkMode: (Resources) -> Boolean = { resources ->
            (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
        },
    ) {
        val view = activity.window.decorView
        val isDarkMode = detectDarkMode(view.resources)

        val impl = Impl ?: if (Build.VERSION.SDK_INT >= 29) {
            EdgeToEdgeApi29()
        } else if (Build.VERSION.SDK_INT >= 26) {
            EdgeToEdgeApi26()
        } else if (Build.VERSION.SDK_INT >= 23) {
            EdgeToEdgeApi23()
        } else if (EdgeToEdgeMiuiApi21.isAvailable()) {
            EdgeToEdgeMiuiApi21() // MIUI 6 implements its own light status bar.
        } else if (EdgeToEdgeFlymeOsApi21.isAvailable()) {
            EdgeToEdgeFlymeOsApi21() // FlymeOS 5 implements its own light status bar.
        } else {
            EdgeToEdgeApi21()
        }.also {
            Impl = it
        }

        impl.setUp(
            window = activity.window,
            view = view,
            statusBarStyleScheme = statusBarStyleScheme,
            navigationBarStyleScheme = navigationBarStyleScheme,
            statusBarScrimColor = statusBarStyleScheme.getScrimColor(isDarkMode),
            navigationBarScrimColor = navigationBarStyleScheme.getScrimColor(isDarkMode),
            statusBarIsDark = statusBarStyleScheme.getSystemBarDarkMode(isDarkMode),
            navigationBarIsDark = navigationBarStyleScheme.getSystemBarDarkMode(isDarkMode)
        )
    }

}