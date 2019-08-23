package sammy.mutahi.forecast.extensions

inline fun<T,R>T.let(f:(T) -> R):R = f(this)