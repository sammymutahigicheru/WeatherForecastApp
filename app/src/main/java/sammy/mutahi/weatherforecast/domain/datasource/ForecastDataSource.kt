package sammy.mutahi.weatherforecast.domain.datasource

import sammy.mutahi.weatherforecast.domain.model.ForecastList

interface ForecastDataSource {
    fun requestForecastByCityName(cityName: String, date: Long): ForecastList?
}