package tat.mukhutdinov.scalablesolutions.asset.domain

import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetDomain
import tat.mukhutdinov.scalablesolutions.asset.domain.boundary.AssetGateway
import javax.inject.Inject

class AssetInteractor @Inject constructor(private val gateway: AssetGateway) : AssetDomain {

}