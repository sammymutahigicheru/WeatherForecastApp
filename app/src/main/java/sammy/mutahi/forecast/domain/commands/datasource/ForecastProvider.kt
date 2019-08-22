package sammy.mutahi.forecast.domain.commands.datasource


import sammy.mutahi.forecast.data.server.ForecastServer
import sammy.mutahi.forecast.data.sqliteDB.ForecastDb
import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import sammy.mutahi.forecast.extensions.firstResult

class ForecastProvider(private val source: List<ForecastDataSource> = ForecastProvider.SOURCES as List<ForecastDataSource>) {
    companion object {
        const val DaY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources { requestSource(it, days, zipCode) }

    private fun requestSource(source: ForecastDataSource, days: Int, zipCode: Long): ForecastList? {
        val res = source.requestForecastByZipCode(zipCode, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    fun requestForecast(id:Long):Forecast =
        requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DaY_IN_MILLIS * DaY_IN_MILLIS

    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = source.firstResult { f(it) }
}