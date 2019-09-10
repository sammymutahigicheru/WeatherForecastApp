package sammy.mutahi.weatherforecast.data.server

import sammy.mutahi.weatherforecast.data.db.ForecastDb
import sammy.mutahi.weatherforecast.data.server.ServerDataMapper
import sammy.mutahi.weatherforecast.domain.datasource.ForecastDataSource
import sammy.mutahi.weatherforecast.domain.model.ForecastList

class ForecastServer (val dataMapper: ServerDataMapper = ServerDataMapper(),
                      val forecastDb: ForecastDb = ForecastDb()): ForecastDataSource{
    override fun requestForecastByCityName(cityName: String, date: Long): ForecastList? {
        val result = ForecastByCityNameRequest(cityName).execute()
        val converted = dataMapper.convertToDomain(result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByCityName(cityName, date)
    }

}