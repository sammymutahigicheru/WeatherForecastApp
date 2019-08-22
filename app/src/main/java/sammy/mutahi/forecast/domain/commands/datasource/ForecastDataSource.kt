package sammy.mutahi.forecast.domain.commands.datasource

import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.domain.commands.domain.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode:Long,date:Long): ForecastList?
    fun requestDayForecast(id:Long): Forecast?
}