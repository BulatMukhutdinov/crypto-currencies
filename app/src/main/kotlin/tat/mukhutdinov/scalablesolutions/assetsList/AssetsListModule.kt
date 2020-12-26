package tat.mukhutdinov.scalablesolutions.assetsList

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import tat.mukhutdinov.scalablesolutions.assetsList.domain.AssetsListInteractor
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListGateway
import tat.mukhutdinov.scalablesolutions.assetsList.gateway.AssetsListRemoteGateway

@Module
@InstallIn(ActivityComponent::class)
abstract class AssetsListModule {

    @Binds
    abstract fun bindAssetsListDomain(
        interactor: AssetsListInteractor
    ): AssetsListDomain

    @Binds
    abstract fun bindAssetsListGateway(
        gateway: AssetsListRemoteGateway
    ): AssetsListGateway
}