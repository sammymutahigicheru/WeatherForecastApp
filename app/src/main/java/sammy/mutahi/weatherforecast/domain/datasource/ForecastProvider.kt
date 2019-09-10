package sammy.mutahi.weatherforecast.domain.datasource

import sammy.mutahi.weatherforecast.data.db.ForecastDb
import sammy.mutahi.weatherforecast.domain.model.ForecastList

class ForecastProvider(val sources:List<ForecastDataSource>,SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }
    fun requestByCityName(cityName: String, days: Int): ForecastList
            = sources.firstResult { requestSource(it, days, zipCode) }
}