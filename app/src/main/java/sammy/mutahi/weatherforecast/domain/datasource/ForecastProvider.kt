package sammy.mutahi.weatherforecast.domain.datasource

import sammy.mutahi.weatherforecast.data.db.ForecastDb
import sammy.mutahi.weatherforecast.data.server.ForecastServer
import sammy.mutahi.weatherforecast.domain.model.ForecastList
import sammy.mutahi.weatherforecast.ui.utils.extensions.firstResult

class ForecastProvider(val sources:List<ForecastDataSource> = SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }
    fun requestByCityName(cityName: String, days: Int): ForecastList
            = sources.firstResult { requestSource(it, days, cityName) }

    private fun requestSource(source: ForecastDataSource, days: Int, cityName: String): ForecastList? {
        val res = source.requestForecastByCityName(cityName, todayTimeSpan())
        return if (res != null && res.size >= days) res else null
    }

    private fun todayTimeSpan() = System.currentTimeMillis() /
            DAY_IN_MILLIS * DAY_IN_MILLIS
}