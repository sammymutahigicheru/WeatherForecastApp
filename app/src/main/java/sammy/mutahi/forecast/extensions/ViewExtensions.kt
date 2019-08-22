package sammy.mutahi.forecast.extensions

import android.view.View;

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY <= 0) animate().translationY(0f)
}