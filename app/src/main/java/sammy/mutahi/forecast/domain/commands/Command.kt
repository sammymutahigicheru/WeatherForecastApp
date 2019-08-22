package sammy.mutahi.forecast.domain.commands

interface Command<out T> {
    fun execute(): T
}