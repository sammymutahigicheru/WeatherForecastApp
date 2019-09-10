package sammy.mutahi.weatherforecast.data.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import sammy.mutahi.weatherforecast.ui.App

/*
* Applied dependency injection via forecast db constructor
* @param ctx
* */

class ForecastDbHelper(ctx:Context = App.instance):ManagedSQLiteOpenHelper(
    ctx, DB_NAME,null, DB_VERSION
    ){
    override fun onCreate(db: SQLiteDatabase?) {
        db!!.createTable(CityForecastTable.NAME, true,
            CityForecastTable.CITY to TEXT + PRIMARY_KEY,
            CityForecastTable.COUNTRY to TEXT)
        db.createTable(DayForecastTable.NAME, true,
            DayForecastTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            DayForecastTable.DATE to INTEGER,
            DayForecastTable.DESCRIPTION to TEXT,
            DayForecastTable.HIGH to INTEGER,
            DayForecastTable.LOW to INTEGER,
            DayForecastTable.ICON_URL to TEXT,
            DayForecastTable.CITY_Name to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.dropTable(CityForecastTable.NAME, true)
        db.dropTable(DayForecastTable.NAME, true)
        onCreate(db)
    }

    companion object{
        val DB_NAME = "forecast.db"
        val DB_VERSION = 1
        val instance by lazy { ForecastDbHelper() }
    }

}