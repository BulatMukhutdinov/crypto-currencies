package tat.mukhutdinov.cryptostock.asset.ui.chart

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import tat.mukhutdinov.cryptostock.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("ViewConstructor, SetTextI18n")
class PriceMarkerView(context: Context, layoutId: Int) : MarkerView(context, layoutId) {

    private val price: TextView = findViewById(R.id.price)
    private val date: TextView = findViewById(R.id.date)

    private val dataFormat = SimpleDateFormat("dd MMM", Locale.getDefault())
    private val d = Date()

    override fun refreshContent(entry: Entry, highlight: Highlight?) {
        price.text = "$${entry.y}"

        d.time = entry.x.toLong()
        date.text = dataFormat.format(d)

        super.refreshContent(entry, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(width / -2f, height * -2f)
    }
}