package sammy.mutahi.forecast.extensions

import android.content.Context
import androidx.core.content.ContextCompat

public fun Context.color(res: Int) = ContextCompat.getColor(this, res)