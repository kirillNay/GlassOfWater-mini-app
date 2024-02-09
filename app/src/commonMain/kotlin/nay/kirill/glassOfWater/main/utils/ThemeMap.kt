package nay.kirill.glassOfWater.main.utils

import nay.kirill.glassOfWater.ui.theme.UiTheme
import nay.kirill.healthcare.domain.Theme

fun Theme.toUi(): UiTheme = when(this) {
    Theme.SYSTEM -> UiTheme.SYSTEM
    Theme.DARK -> UiTheme.DARK
    Theme.LIGHT -> UiTheme.LIGHT
    Theme.ADAPTIVE -> UiTheme.ADAPTIVE
}