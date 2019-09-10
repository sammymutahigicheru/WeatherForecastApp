package sammy.mutahi.weatherforecast.data.db

import sammy.mutahi.weatherforecast.domain.model.Forecast
import sammy.mutahi.weatherforecast.domain.model.ForecastList

/*
* converts classes from db to domain classes
* */

class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(city, it) }
        CityForecast(city, country, daily)
    }
    private fun convertDayFromDomain(citName: String, forecast: Forecast) =
        with(forecast) {
            DayForecast(date, description, high, low, iconUrl, cityName)
        }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(city,it) }
        ForecastList(city, country, daily)
    }
    private fun convertDayToDomain(cityName:String,dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl,cityName)
    }

}