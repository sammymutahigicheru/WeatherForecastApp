package sammy.mutahi.forecast.extensions.delegates

object DelegatesExt {
    fun<T> notNullSingleValue()= NotNullSingleValueVar<T>()
}