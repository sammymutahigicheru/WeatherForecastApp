package sammy.mutahi.forecast.extensions

/*
* This extension pairs key and values
*
*
* */

fun <K, V : Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
    map { Pair(it.key, it.value!!) }.toTypedArray()

/*
* iterates through db,if a match is found return result else throws an exception
* */

inline fun <T, R:Any>Iterable<T>.firstResult(predicate:(T)->R?):R{
    for(element in this){
        val result = predicate(element)
        if(result != null) return result
    }
    throw NoSuchElementException("No Element Matching Predicate was found")
}