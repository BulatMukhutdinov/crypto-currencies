package tat.mukhutdinov.scalablesolutions.assetsList.ui

import android.os.Bundle
import android.view.View
import tat.mukhutdinov.android.structure.StructureFragment
import tat.mukhutdinov.scalablesolutions.assetsList.ui.boundary.AssetsListViewModel
import tat.mukhutdinov.scalablesolutions.databinding.AssetsListBinding
import tat.mukhutdinov.android.structure.viewModel

class AssetsListFragment : StructureFragment<AssetsListBinding>() {

    override val viewModel: AssetsListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
    }
}