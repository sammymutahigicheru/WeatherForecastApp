package sammy.mutahi.forecast.ui.adapter.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import android.view.LayoutInflater
import sammy.mutahi.forecast.domain.commands.domain.ForecastList
import android.view.View;
import android.widget.ImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_forecast_list.view.*
import org.jetbrains.anko.find
import sammy.mutahi.forecast.R
import sammy.mutahi.forecast.domain.commands.domain.Forecast
import sammy.mutahi.forecast.ui.adapter.utils.ctx
import java.text.DateFormat
import java.util.*

class ForecastListAdapter(
    val weeklyForecast: ForecastList,
    val itemClick:(Forecast) -> Unit
) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.ctx)
            .inflate(R.layout.row_forecast_list,p0,false)
        return ViewHolder(view,itemClick)
    }

    override fun getItemCount(): Int = weeklyForecast.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindForecast(weeklyForecast[p1])
    }


    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {


        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.get().load(forecast.iconUrl).into(itemView.icon)
                itemView.date.text = convertDate(date)
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

        private fun convertDate(date: Long): String {
            val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
            return df.format(date)
        }

    }
}