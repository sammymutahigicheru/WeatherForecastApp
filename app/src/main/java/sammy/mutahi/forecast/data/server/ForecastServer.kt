package sammy.mutahi.forecast.data.server

import sammy.mutahi.forecast.data.sqliteDB.ForecastDb

class ForecastServer(private val dataMapper: ServerDataMapper= ServerDataMapper(),
                     private val forecastDb: ForecastDb= ForecastDb()
){

}