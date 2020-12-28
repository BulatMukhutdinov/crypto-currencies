package tat.mukhutdinov.scalablesolutions.asset.domain

import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetGateway
import tat.mukhutdinov.scalablesolutions.asset.domain.model.AssetTimeSeries
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class AssetInteractor @Inject constructor(private val gateway: AssetGateway) : AssetDomain {

    override suspend fun fetchTimeSeries(assetId: String): List<AssetTimeSeries> {
        val lastMonth = Calendar.getInstance().apply {
            add(Calendar.MONTH, -1)
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        val startDate: String = dateFormat.format(lastMonth.time)

        return gateway.fetchTimeSeries(assetId, startDate)
    }
}