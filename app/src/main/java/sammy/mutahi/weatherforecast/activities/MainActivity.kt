package sammy.mutahi.weatherforecast.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.data.server.ForecastRequest

class MainActivity : AppCompatActivity() {

    /*
    * open weather api
    * */
    val url =
        "http://api.openweathermap.org/data/2.5/forecast/daily?APPID=15646a06818f61f7b8d7823ca833e1ce&q=mombasa&mode=json&units=metri\\%20c&cnt=7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        forecast_list.layoutManager = LinearLayoutManager(this)
        doAsync {
            //perform request
            ForecastRequest(url).run()
            uiThread {
                toast("ForecastRequest Performed")
            }
        }
    }
}
