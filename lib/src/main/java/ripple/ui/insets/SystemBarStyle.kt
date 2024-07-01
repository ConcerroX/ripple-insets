package ripple.ui.insets

import android.graphics.Color
import androidx.annotation.ColorInt

class SystemBarStyle(
    @ColorInt internal val scrimColor: Int = Color.TRANSPARENT,
    internal val systemBarDarkMode: Boolean,
)