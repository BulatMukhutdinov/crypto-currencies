package tat.mukhutdinov.scalablesolutions.asset

import org.koin.dsl.module
import retrofit2.Retrofit
import tat.mukhutdinov.android.structure.viewModel
import tat.mukhutdinov.scalablesolutions.asset.gateway.boundary.AssetApi
import tat.mukhutdinov.scalablesolutions.asset.gateway.converter.AssetConverter
import tat.mukhutdinov.scalablesolutions.assetsList.ui.AssetsListRetainedViewModel
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListViewModel

object AssetModule {

    val module = module {

        factory {
            val retrofit: Retrofit = get()

            retrofit.create(AssetApi::class.java)
        }

        factory {
            AssetConverter()
        }
    }
}