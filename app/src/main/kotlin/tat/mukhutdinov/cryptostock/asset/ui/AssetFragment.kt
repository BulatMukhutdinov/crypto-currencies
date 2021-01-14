package tat.mukhutdinov.cryptostock.asset.ui

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.android.material.chip.Chip
import com.google.android.material.color.MaterialColors
import dagger.hilt.android.AndroidEntryPoint
import tat.mukhutdinov.cryptostock.R
import tat.mukhutdinov.cryptostock.asset.domain.model.AssetTimeSeries
import tat.mukhutdinov.cryptostock.asset.domain.model.OfficialLink
import tat.mukhutdinov.cryptostock.asset.ui.chart.DateFormatter
import tat.mukhutdinov.cryptostock.asset.ui.chart.PriceMarkerView
import tat.mukhutdinov.cryptostock.databinding.AssetBinding
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.BaseFragment

@AndroidEntryPoint
class AssetFragment : BaseFragment<AssetBinding>() {

    override val viewModel: AssetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.details.movementMethod = LinkMovementMethod.getInstance()

        setupLinks()

        setupTimeSeries()

        setupLoading()
    }

    private fun setupLoading() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                viewBinding.error.container.isVisible = false

                viewBinding.loading.show()
            } else {
                viewBinding.loading.hide()
            }
        }
    }

    private fun setupTimeSeries() {
        with(viewBinding.timeSeries) {
            isDragEnabled = true
            legend.isEnabled = false
            description.isEnabled = false
            axisRight.isEnabled = false

            setDrawGridBackground(false)
            setScaleEnabled(true)
            xAxis.valueFormatter = DateFormatter()

            val textColor = MaterialColors.getColor(requireContext(), R.attr.colorText, Color.BLACK)
            xAxis.textColor = textColor
            axisLeft.textColor = textColor

            val markerView = PriceMarkerView(context, R.layout.asset_chart_marker)
            markerView.chartView = this
            marker = markerView
        }

        observeEntries()
    }

    private fun observeEntries() {
        viewModel.timeSeries.observe(viewLifecycleOwner) {
            it.fold(::onLoadSuccess, ::onLoadFail)
        }
    }

    private fun onLoadSuccess(data: List<AssetTimeSeries>) {
        viewBinding.error.container.isVisible = false

        val entries = mutableListOf<Entry>()

        data.forEach { timeSeries ->
            entries.add(Entry(timeSeries.date, timeSeries.price))
        }

        viewBinding.emptyTimeSeries.isVisible = entries.isEmpty()
        viewBinding.timeSeries.isVisible = entries.isNotEmpty()

        viewBinding.timeSeries.data = createLineData(entries)
        viewBinding.timeSeries.invalidate()
    }

    private fun onLoadFail(throwable: Throwable) {
        viewBinding.error.container.isVisible = true
        viewBinding.error.message.text = throwable.localizedMessage
    }

    private fun createLineData(entries: List<Entry>): LineData {
        val dataSet = LineDataSet(entries, "")

        dataSet.color = MaterialColors.getColor(requireContext(), R.attr.colorSecondary, Color.BLACK)
        dataSet.setCircleColor(MaterialColors.getColor(requireContext(), R.attr.colorSecondaryVariant, Color.BLACK))
        dataSet.setDrawCircleHole(false)
        dataSet.setDrawValues(false)

        return LineData(dataSet)
    }

    private fun setupLinks() {
        viewModel.asset.officialLinks.forEach {
            addLink(it)
        }
    }

    private fun addLink(officialLink: OfficialLink) {
        val chip = Chip(context).apply {
            text = officialLink.name
            setOnClickListener { openLink(officialLink.link) }
        }

        viewBinding.links.addView(chip)
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        activity?.packageManager?.let {
            if (intent.resolveActivity(it) != null) {
                startActivity(intent)
            }
        }
    }
}