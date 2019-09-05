package sammy.mutahi.weatherforecast.domain

/*
* Responsible for perfoming use cases of the app
* */

interface Command<out T> {
    fun execute():T
}