package sammy.mutahi.forecast.data.server

import sammy.mutahi.forecast.data.sqliteDB.ForecastDb
import sammy.mutahi.forecast.domain.commands.datasource.ForecastDataSource
import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.domain.commands.domain.ForecastList

class ForecastServer(
    private val dataMapper: ServerDataMapper = ServerDataMapper(),
    private val forecastDb: ForecastDb = ForecastDb()
) : ForecastDataSource {


    override fun requestForecastByZipCode(zipCode: Long, date: Long):
            ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long): Forecast? = throw UnsupportedOperationException()

}