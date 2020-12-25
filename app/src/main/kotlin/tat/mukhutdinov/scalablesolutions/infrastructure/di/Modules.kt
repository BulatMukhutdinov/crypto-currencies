package tat.mukhutdinov.scalablesolutions.infrastructure.di

import tat.mukhutdinov.scalablesolutions.asset.AssetModule
import tat.mukhutdinov.scalablesolutions.assetsList.AssetsListModule
import tat.mukhutdinov.scalablesolutions.infrastructure.InfrastructureModule

object Modules {

    val modules = listOf(
        InfrastructureModule.module,
        AssetsListModule.module,
        AssetModule.module,
    )
}