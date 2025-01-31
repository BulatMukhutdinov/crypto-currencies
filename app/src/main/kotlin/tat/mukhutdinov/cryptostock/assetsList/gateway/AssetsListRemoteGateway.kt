package tat.mukhutdinov.cryptostock.assetsList.gateway

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.liveData
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.asset.gateway.converter.AssetConverter
import tat.mukhutdinov.cryptostock.assetsList.domain.boundary.AssetsListGateway
import tat.mukhutdinov.cryptostock.assetsList.gateway.boundary.AssetsListApi
import javax.inject.Inject

class AssetsListRemoteGateway @Inject constructor(
    private val api: AssetsListApi,
    private val converter: AssetConverter
) : AssetsListGateway, PagingSource<Int, Asset>() {

    override fun getAssets(): LiveData<PagingData<Asset>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { this }
        ).liveData

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Asset> {
        val page = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = api.fetchAssetsList(limit = params.loadSize, page = page)

            if (response.data == null) {
                throw RuntimeException("${response.status?.errorMessage} Error code = ${response.status?.errorCode}")
            }

            val assets = response.data.map(converter::convert)

            val prevKey = if (page == STARTING_PAGE_INDEX) {
                null
            } else {
                page - 1
            }

            val nextKey = if (assets.isEmpty()) {
                null
            } else {
                page + 1
            }

            LoadResult.Page(
                data = assets,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
        private const val NETWORK_PAGE_SIZE = 20
    }
}