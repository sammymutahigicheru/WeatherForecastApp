package sammy.mutahi.weatherforecast.data.db

/*
* Model classes for db
* using map delegate
* making properties to be the same with column names makes mapping to db easy
* */

class CityForecast(val map:MutableMap<String,Any>,
                   val dailyForecast:List<DayForecast>){
    var _id: Long by map
    var city: String by map
    var country: String by map

    constructor(city: String, country: String,
                dailyForecast: List<DayForecast>)
            : this(HashMap(), dailyForecast) {
        this.city = city
        this.country = country
    }

}

class DayForecast(var map:MutableMap<String,Any>){
    var _id:Long by map
    var date: Long by map
    var description: String by map
    var high: Int by map
    var low: Int by map
    var iconUrl: String by map
    var cityName: String by map

    constructor(date: Long, description: String, high: Int, low: Int,
                iconUrl: String, cityName: String) : this(HashMap()){

        this.date = date
        this.description = description
        this.high = high
        this.low = low
        this.iconUrl = iconUrl
        this.cityName = cityName //foreign key
    }
}