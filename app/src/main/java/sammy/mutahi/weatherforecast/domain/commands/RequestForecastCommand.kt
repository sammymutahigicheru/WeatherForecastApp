package sammy.mutahi.weatherforecast.domain.commands

import sammy.mutahi.weatherforecast.data.server.ForecastByCityNameRequest

import sammy.mutahi.weatherforecast.domain.mapper.ForecastDataMapper
import sammy.mutahi.weatherforecast.domain.model.ForecastList

class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastByCityNameRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}