package sammy.mutahi.weatherforecast.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.view.View;
import android.view.LayoutInflater;
import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import sammy.mutahi.weatherforecast.R
import sammy.mutahi.weatherforecast.domain.model.Forecast
import sammy.mutahi.weatherforecast.domain.model.ForecastList
import sammy.mutahi.weatherforecast.ui.utils.ctx

class ForecastListAdapter(val weekForecast: ForecastList,
                          val itemClick: (Forecast) -> Unit) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.ctx)
            .inflate(R.layout.item_forecast,p0, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindForecast(weekForecast[p1])
    }

    class ViewHolder(view: View, val itemClick: (Forecast) ->Unit) : RecyclerView.ViewHolder(view) {
        private val iconView = view.find<ImageView>(R.id.icon)
        private val dateView = view.find<TextView>(R.id.date)
        private val descriptionView =
            view.find<TextView>(R.id.description)
        private val maxTemperatureView =
            view.find<TextView>(R.id.maxTemperature)
        private val minTemperatureView =
            view.find<TextView>(R.id.minTemperature)

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.get().load(iconUrl).into(iconView)
                dateView.text = date
                descriptionView.text = description
                maxTemperatureView.text = "$high"
                minTemperatureView.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}