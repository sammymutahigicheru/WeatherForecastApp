package sammy.mutahi.forecast.data.server

import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import sammy.mutahi.forecast.domain.commands.domain.Forecast as ModelForecast

/*
* converts data from server to domain
* */

class ServerDataMapper {

    fun convertToDomain(zipCode: Long, forecast: ForecastResult) = with(forecast) {
        ForecastList(zipCode, city.name, city.country, convertForecastListToDomain(list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast) = with(forecast) {
        ModelForecast(-1,dt, weather[0].description, temp.max.toInt(), temp.min.toInt(),
            generateIconUrl(weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String) = "http://openweathermap.org/img/w/$iconCode.png"
}