package sammy.mutahi.weatherforecast.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.domain.commands.RequestForecastCommand
import sammy.mutahi.weatherforecast.ui.adapters.ForecastListAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        forecast_list.layoutManager = LinearLayoutManager(this)

        doAsync {
            val result = RequestForecastCommand("mombasa").execute()
            uiThread {
                val adapter = ForecastListAdapter(result) { toast(it.date) }
                forecast_list.adapter = adapter
            }
        }
    }
}
