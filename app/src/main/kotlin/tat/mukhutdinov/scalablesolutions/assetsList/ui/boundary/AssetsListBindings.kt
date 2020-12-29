package tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.boundary.ErrorBindings

interface AssetsListBindings {

    val errorBindings: ErrorBindings

    fun onAssetClicked(asset: Asset)
}