package sammy.mutahi.forecast.domain.commands

import sammy.mutahi.forecast.data.server.ForecastipCodeByZRequest
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import sammy.mutahi.forecast.domain.commands.mappers.ForecastDataMapper

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastipCodeByZRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}