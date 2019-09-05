package sammy.mutahi.weatherforecast.data.sqliteDatabase

import sammy.mutahi.weatherforecast.domain.model.Forecast
import sammy.mutahi.weatherforecast.domain.model.ForecastList

class DbDataMapper {

    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map { convertDayFromDomain(id, it) }
        CityForecast(city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(id,date, description, high, low, iconUrl, cityId)
    }

    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }

    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}