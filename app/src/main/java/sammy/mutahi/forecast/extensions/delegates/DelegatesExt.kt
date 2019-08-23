package sammy.mutahi.forecast.extensions.delegates

import android.content.Context
import kotlin.reflect.KProperty

object DelegatesExt {
    fun<T> notNullSingleValue()= NotNullSingleValue<T>()

    fun longPreference(context: Context, name: String, default: Long) =
        LongPreference(context, name, default)
}

