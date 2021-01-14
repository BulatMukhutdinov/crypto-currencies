package tat.mukhutdinov.cryptostock.assetsList.ui.boundary

import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.boundary.ErrorBindings

interface AssetsListBindings {

    val errorBindings: ErrorBindings

    fun onAssetClicked(asset: Asset)
}