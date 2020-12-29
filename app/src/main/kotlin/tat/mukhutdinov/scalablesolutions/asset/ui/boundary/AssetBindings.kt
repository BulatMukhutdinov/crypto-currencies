package tat.mukhutdinov.scalablesolutions.asset.ui.boundary

import tat.mukhutdinov.scalablesolutions.asset.domain.model.Asset
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.boundary.ErrorBindings

interface AssetBindings {

    val asset: Asset

    val errorBindings: ErrorBindings
}