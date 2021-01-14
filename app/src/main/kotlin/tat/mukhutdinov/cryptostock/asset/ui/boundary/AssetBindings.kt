package tat.mukhutdinov.cryptostock.asset.ui.boundary

import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.boundary.ErrorBindings

interface AssetBindings {

    val asset: Asset

    val errorBindings: ErrorBindings
}