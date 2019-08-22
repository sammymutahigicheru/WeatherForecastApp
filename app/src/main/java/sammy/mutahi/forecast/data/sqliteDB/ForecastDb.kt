package sammy.mutahi.forecast.data.sqliteDB

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import sammy.mutahi.forecast.domain.commands.datasource.ForecastDataSource
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import sammy.mutahi.forecast.extensions.clear
import sammy.mutahi.forecast.extensions.parseList
import sammy.mutahi.forecast.extensions.parseOpt
import sammy.mutahi.forecast.extensions.toVarargArray

class ForecastDb(
    val forecastDBHelper: ForecastDBHelper = ForecastDBHelper.instance,
    val dataMapper: DbDataMapper = DbDataMapper()
) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDBHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? " + "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, zipCode.toString(), date.toString())
            .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.ID} =?", zipCode.toString())
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        if (city != null) dataMapper.convertToDomain(city) else null

    }

    fun saveForecast(forecast: ForecastList) = forecastDBHelper.use {
        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)
        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach {
                insert(DayForecastTable.NAME, *it.map.toVarargArray())
            }
        }

    }
}