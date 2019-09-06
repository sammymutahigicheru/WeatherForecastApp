package sammy.mutahi.weatherforecast.ui

import android.app.Application
import sammy.mutahi.weatherforecast.ui.utils.extensions.DelegatesExt

class App:Application(){
    companion object{
        var instance:App by DelegatesExt.notNullSingleValue()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}