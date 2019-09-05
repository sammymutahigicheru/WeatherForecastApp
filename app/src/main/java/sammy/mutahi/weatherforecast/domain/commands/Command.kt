package sammy.mutahi.weatherforecast.domain.commands

/*
* Responsible for perfoming use cases of the app
* it will execute an operation and return an object of class specified in:
* @param T
* */

interface Command<out T> {
    fun execute():T
}