package sammy.mutahi.forecast.ui.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.*
import sammy.mutahi.forecast.R
import sammy.mutahi.forecast.ui.adapter.adapter.ForecastListAdapter
import sammy.mutahi.forecast.domain.commands.RequestForecastCommand
import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.ui.adapter.adapter.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),ToolBarManager {
    override val toolbar: Toolbar by lazy {
        find<Toolbar>(R.id.toolbar)
    }
//    val url =
//        "https://api.openweathermap.org/data/2.5/weather/daily?q=Nairobi,uk&APPID=4851ff9dc2036e8b92865366cc5ff1ce&q=94043&mode=json&units=metri\\c&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)

        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {

                val adapter = ForecastListAdapter(result) {
                    //refied function
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                        DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle = "${result.city} (${result.country}) "
            }
    }
    }

}
