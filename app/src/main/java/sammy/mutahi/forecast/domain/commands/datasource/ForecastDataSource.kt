package sammy.mutahi.forecast.domain.commands.datasource

import sammy.mutahi.forecast.domain.commands.domain.ForecastList

interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode:Long,date:Long): ForecastList?
}