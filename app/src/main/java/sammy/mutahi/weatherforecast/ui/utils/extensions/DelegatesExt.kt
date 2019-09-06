package sammy.mutahi.weatherforecast.ui.utils.extensions

import android.content.Context
import kotlin.reflect.KProperty

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
    fun stringPreference(context: Context,name: String,default: String)
    =StringPreference(context,name,default)
}

class NotNullSingleValueVar<T> {

    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T =
        value ?: throw IllegalStateException("${property.name} not initialized")

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

/*
*queries preference when get is called and saves it when set is called
* */
class StringPreference(val ctx: Context, val name: String, val default: String) {
    val prefs by lazy {
        ctx.getSharedPreferences("default", Context.MODE_PRIVATE)
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String? = prefs.getString(name, default)
    operator fun setValue(thisRef: Any?, property: KProperty<*>,value:String){
        prefs.edit().putString(name,value).apply()
    }
}