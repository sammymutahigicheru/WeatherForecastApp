package sammy.mutahi.forecast.domain.commands

import sammy.mutahi.forecast.data.server.ForecastRequest
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import sammy.mutahi.forecast.domain.commands.mappers.ForecastDataMapper

class RequestForecastCommand(private val zipCode: Long) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(zipCode, forecastRequest.execute())
    }
}