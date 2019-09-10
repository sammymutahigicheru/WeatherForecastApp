package sammy.mutahi.weatherforecast.data.db

import org.jetbrains.anko.db.select
import sammy.mutahi.weatherforecast.domain.mapper.ForecastDataMapper

/*
* takes care of db operations ${inserting and retrieving data}
* */
class ForecastDb (val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                  val dataMapper:DbDataMapper = DbDataMapper()
){
    fun requestForecastByCityName(cityName:String,date:Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_Name} = ? " +
                "AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
            .whereSimple(dailyRequest, cityName, date.toString())
            .parseList { DayForecast(HashMap(it)) }
    }
}