package sammy.mutahi.forecast.domain.commands

import sammy.mutahi.forecast.domain.commands.datasource.ForecastProvider
import sammy.mutahi.forecast.domain.commands.domain.Forecast

class RequestDayForecastCommand(
    val id: Long,
    val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<Forecast> {
    override fun execute(): Forecast = forecastProvider.requestForecast(id)

}