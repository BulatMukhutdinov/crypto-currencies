package tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset

interface AssetsListBindings {

    fun onAssetClicked(asset: Asset)

    fun onRetryClicked()
}