package sammy.mutahi.forecast.extensions

import android.widget.TextView

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(v)