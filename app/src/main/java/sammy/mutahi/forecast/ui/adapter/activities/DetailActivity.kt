package sammy.mutahi.forecast.ui.adapter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import sammy.mutahi.forecast.R
import sammy.mutahi.forecast.domain.commands.RequestDayForecastCommand
import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.extensions.color
import sammy.mutahi.forecast.extensions.textColor
import sammy.mutahi.forecast.extensions.toDateString
import java.text.DateFormat

class DetailActivity : AppCompatActivity() {

    companion object{
        val ID="DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        title = intent.getStringExtra(CITY_NAME)

        doAsync {
            val result = RequestDayForecastCommand(intent.getLongExtra(ID,-1)).execute()
            uiThread {
                bindForecast(result)
            }
        }
    }

    private fun bindForecast(forecast: Forecast)= with(forecast) {
        Picasso.get().load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first.toString()}º"
        it.second.textColor = color(when (it.first) {
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}
