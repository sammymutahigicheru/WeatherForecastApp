package sammy.mutahi.weatherforecast.domain.model


/*
* The first command will request data to API and convert it to these domain classes
* */

data class ForecastList(val city: String, val country: String,
                        private val dailyForecast: List<Forecast>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}

data class Forecast(val date: String, val description: String, val high: Int, val low: Int,
                    val iconUrl: String,val cityName:String)