package tat.mukhutdinov.scalablesolutions.asset

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import tat.mukhutdinov.scalablesolutions.asset.domain.AssetInteractor
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetGateway
import tat.mukhutdinov.scalablesolutions.asset.gateway.AssetRemoteGateway
import tat.mukhutdinov.scalablesolutions.asset.gateway.boundary.AssetApi

@Module
@InstallIn(ApplicationComponent::class)
abstract class AssetsListModule {

    @Binds
    abstract fun bindAssetGateway(
        gateway: AssetRemoteGateway
    ): AssetGateway

    @Binds
    abstract fun bindAssetDomain(
        interactor: AssetInteractor
    ): AssetDomain

    companion object {

        @Provides
        fun provideAssetApi(retrofit: Retrofit): AssetApi =
            retrofit.create(AssetApi::class.java)
    }
}