package sammy.mutahi.weatherforecast.ui.adapters

import sammy.mutahi.weatherforecast.domain.model.Forecast

interface OnItemClickListener {
    operator fun invoke(forecast: Forecast)
}