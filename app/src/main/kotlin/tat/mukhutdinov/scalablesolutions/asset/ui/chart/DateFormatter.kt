package tat.mukhutdinov.scalablesolutions.asset.ui.chart

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateFormatter : ValueFormatter() {

    private val dataFormat = SimpleDateFormat("dd.MM", Locale.getDefault())

    private val date = Date()

    override fun getFormattedValue(value: Float): String {
        date.time = value.toLong()

        return dataFormat.format(date)
    }
}