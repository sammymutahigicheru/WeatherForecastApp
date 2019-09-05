package sammy.mutahi.weatherforecast.ui

import android.app.Application

/*
* This makes it easy to access application context
 * and enables us to keep code simple
 * and in different layers
* */

class App:Application() {
    companion object{
        private var instance:Application?=null
        fun instance() = instance!!
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}