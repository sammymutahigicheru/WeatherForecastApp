package sammy.mutahi.forecast.ui.adapter.adapter

import sammy.mutahi.forecast.domain.commands.domain.Forecast

interface OnItemClickListener {
    operator fun invoke(forecast:Forecast)
}