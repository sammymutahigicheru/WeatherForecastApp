package sammy.mutahi.weatherforecast.ui

import android.app.Application
import sammy.mutahi.weatherforecast.ui.utils.DelegatesExt
import kotlin.properties.Delegates

/*
* This makes it easy to access application context
 * and enables us to keep code simple
 * and in different layers
* */

class App:Application() {
    companion object{
        lateinit var instance:App
            private set
        //prevent instance from being modified inside the app

    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}