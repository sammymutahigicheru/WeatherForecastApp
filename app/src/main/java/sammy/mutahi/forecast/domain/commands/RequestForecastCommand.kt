package sammy.mutahi.forecast.domain.commands

import sammy.mutahi.forecast.data.server.ForecastByZipCodeRequest
import sammy.mutahi.forecast.domain.commands.datasource.ForecastProvider
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import sammy.mutahi.forecast.domain.commands.mappers.ForecastDataMapper

class RequestForecastCommand(
    private val zipCode: Long,
    val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute(): ForecastList {
        val forecastRequest = ForecastByZipCodeRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, DAYS)
    }
}