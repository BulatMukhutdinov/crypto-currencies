package tat.mukhutdinov.cryptostock.assetsList

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import tat.mukhutdinov.cryptostock.assetsList.domain.AssetsListInteractor
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListGateway
import tat.mukhutdinov.cryptostock.assetsList.gateway.AssetsListRemoteGateway
import tat.mukhutdinov.cryptostock.assetsList.gateway.boundary.AssetsListApi

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

    companion object {

        @Provides
        fun provideAssetApi(retrofit: Retrofit): AssetsListApi =
            retrofit.create(AssetsListApi::class.java)
    }
}