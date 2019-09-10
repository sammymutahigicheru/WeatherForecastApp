package sammy.mutahi.weatherforecast.domain.commands

import sammy.mutahi.weatherforecast.data.server.ForecastByCityNameRequest
import sammy.mutahi.weatherforecast.domain.datasource.ForecastProvider


import sammy.mutahi.weatherforecast.domain.model.ForecastList

class RequestForecastCommand(
    private val cityName: String,
    private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<ForecastList> {

    companion object {
        const val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByCityName(cityName, DAYS)
}