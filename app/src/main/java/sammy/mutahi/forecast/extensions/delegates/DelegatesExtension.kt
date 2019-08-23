package sammy.mutahi.forecast.extensions.delegates

import android.content.Context
import kotlin.reflect.KProperty

class NotNullSingleValue<T> {
    private var value: T? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: throw IllegalStateException(
            "${property.name} " +
                    "not initialized"
        )
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

/*
* we create a lazy access to preferences
* when get is called it retrieves long value(zipCode) in this case
* */

class Preference<T>(val context: Context, val name: String, val default: T) {

    val prefs by lazy {
        context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return findPreference(name, default)
    }

    private fun <T> findPreference(name: String, default: T): T = with(prefs) {
        val res: Any = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> throw IllegalArgumentException(
                "This type can't be saved into Preferences"
            )
        }
        res as T
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Long) {
        putPreference(name, value)
    }

    private fun <U> putPreference(name: String, value: U) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw IllegalArgumentException("This type can be saved into Preferences")
        }.apply()
    }
}