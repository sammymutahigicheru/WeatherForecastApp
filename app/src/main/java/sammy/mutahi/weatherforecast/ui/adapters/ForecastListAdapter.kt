package sammy.mutahi.weatherforecast.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import sammy.mutahi.weatherforecast.domain.model.ForecastList

class ForecastListAdapter(val weekForecast: ForecastList) :
    RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(TextView(p0.context))
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        with(weekForecast[p1]){
            p0.textView.text = "$date - $description - $high/$low"
        }
    }

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}