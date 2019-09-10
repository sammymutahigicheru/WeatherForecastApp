package sammy.mutahi.weatherforecast.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import sammy.mutahi.weatherforecast.domain.datasource.ForecastDataSource
import sammy.mutahi.weatherforecast.domain.model.ForecastList
import sammy.mutahi.weatherforecast.ui.utils.extensions.clear
import sammy.mutahi.weatherforecast.ui.utils.extensions.parseList
import sammy.mutahi.weatherforecast.ui.utils.extensions.parseOpt
import sammy.mutahi.weatherforecast.ui.utils.extensions.toVarargArray

/*
* takes care of db operations ${inserting and retrieving data}
* */
class ForecastDb (val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                  val dataMapper:DbDataMapper = DbDataMapper()
): ForecastDataSource {
    override fun requestForecastByCityName(cityName:String,date:Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_Name} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, cityName, date.toString())
            .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
            .whereSimple("${CityForecastTable.CITY} = ?", cityName)
            .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        if (city != null) dataMapper.convertToDomain(city) else null
    }
    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {
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