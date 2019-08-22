package sammy.mutahi.forecast.domain.commands

import sammy.mutahi.forecast.data.server.ForecastByZipCodeRequest
import sammy.mutahi.forecast.domain.commands.datasource.ForecastProvider
import sammy.mutahi.forecast.domain.commands.domain.ForecastList


class RequestForecastCommand(
    private val zipCode: Long,
    val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList = forecastProvider.requestByZipCode(zipCode, DAYS)
}