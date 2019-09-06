package sammy.mutahi.weatherforecast.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.domain.commands.RequestForecastCommand
import sammy.mutahi.weatherforecast.ui.adapters.ForecastListAdapter
import sammy.mutahi.weatherforecast.ui.utils.ToolBarManager
import sammy.mutahi.weatherforecast.ui.utils.extensions.DelegatesExt

class MainActivity : AppCompatActivity(),
    ToolBarManager {
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }

    val cityName: String by DelegatesExt.stringPreference(this, Settings.CITY_NAME,
        Settings.DEFAULT_CITY_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecast_list.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecast_list)

    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }
    fun loadForecast() = doAsync {
        val result = RequestForecastCommand(cityName).execute()
        uiThread {
            val adapter = ForecastListAdapter(result) { toast(it.date) }
            forecast_list.adapter = adapter
            toolbarTitle = "${result.city} (${result.country})"
        }
    }
}
