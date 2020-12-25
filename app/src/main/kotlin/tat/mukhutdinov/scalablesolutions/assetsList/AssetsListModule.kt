package tat.mukhutdinov.scalablesolutions.assetsList

import org.koin.dsl.module
import tat.mukhutdinov.android.structure.viewModel
import tat.mukhutdinov.scalablesolutions.assetsList.domain.AssetsListInteractor
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListGateway
import tat.mukhutdinov.scalablesolutions.assetsList.gateway.AssertsListRemoteGateway
import tat.mukhutdinov.scalablesolutions.assetsList.ui.AssetsListRetainedViewModel
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListViewModel

object AssetsListModule {

    val module = module {

        viewModel<AssetsListViewModel> {
            AssetsListRetainedViewModel(get())
        }

        factory<AssetsListGateway> {
            AssertsListRemoteGateway(get(), get())
        }

        factory<AssetsListDomain> {
            AssetsListInteractor(get())
        }
    }
}