package sammy.mutahi.weatherforecast.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.ActionMode
import kotlinx.android.synthetic.main.activity_settings.*
import org.jetbrains.anko.find
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.ui.utils.ToolBarManager
import sammy.mutahi.weatherforecast.ui.utils.extensions.DelegatesExt

class Settings : AppCompatActivity(),
    ToolBarManager {

    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }


    companion object {
        val CITY_NAME = "cityName"
        val DEFAULT_CITY_NAME = "mombasa"
    }

    var cityName: String by DelegatesExt.stringPreference(this, CITY_NAME, DEFAULT_CITY_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        initToolbar()
        toolbarTitle = "Settings"
        city_name.setText(cityName)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        cityName = city_name.text.toString()
    }
}
