package sammy.mutahi.weatherforecast.data.server

import com.google.gson.Gson
import java.net.URL

class ForecastByCityNameRequest(private val cityName: String) {

    companion object {
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "http://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    fun execute(): ForecastResult {
        val forecastJsonStr = URL(COMPLETE_URL + cityName).readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}