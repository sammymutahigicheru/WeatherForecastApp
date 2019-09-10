package sammy.mutahi.weatherforecast.ui.utils.extensions

import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.SelectQueryBuilder

//returns map row parser which uses columns as keys
fun <T : Any> SelectQueryBuilder.parseList(
    parser: (Map<String, Any?>) -> T): List<T> =
    parseList(object : MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
    })

fun <T : Any> SelectQueryBuilder.parseOpt(
    parser: (Map<String, Any?>) -> T): T? =
    parseOpt(object : MapRowParser<T> {
        override fun parseRow(columns: Map<String, Any?>): T = parser(columns)
    })