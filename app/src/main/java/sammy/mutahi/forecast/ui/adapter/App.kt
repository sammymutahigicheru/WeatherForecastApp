package sammy.mutahi.forecast.ui.adapter

import android.app.Application
import androidx.appcompat.app.ActionBarDrawerToggle
import sammy.mutahi.forecast.ui.adapter.delegates.DelegatesExt

class App: Application() {
    companion object{
        var instance:App by DelegatesExt.notNullSingleValue()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}