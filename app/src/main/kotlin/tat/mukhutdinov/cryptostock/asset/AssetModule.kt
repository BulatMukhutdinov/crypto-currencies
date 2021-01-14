package tat.mukhutdinov.cryptostock.asset

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import tat.mukhutdinov.cryptostock.asset.domain.AssetInteractor
import tat.mukhutdinov.cryptostock.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.cryptostock.asset.domain.boundary.AssetGateway
import tat.mukhutdinov.cryptostock.asset.gateway.AssetRemoteGateway
import tat.mukhutdinov.cryptostock.asset.gateway.boundary.AssetApi

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