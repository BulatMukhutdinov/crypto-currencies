package tat.mukhutdinov.scalablesolutions.assetsList.ui

import tat.mukhutdinov.android.structure.StructureRetainedViewModel
import tat.mukhutdinov.scalablesolutions.assetsList.domain.boundary.AssetsListDomain
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListViewModel

class AssetsListRetainedViewModel(private val domain: AssetsListDomain) : StructureRetainedViewModel(), AssetsListViewModel, AssetsListBindings {

}