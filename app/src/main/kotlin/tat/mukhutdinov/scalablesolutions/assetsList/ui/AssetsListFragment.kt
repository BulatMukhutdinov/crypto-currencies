package tat.mukhutdinov.scalablesolutions.assetsList.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tat.mukhutdinov.scalablesolutions.databinding.AssetsListBinding
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseFragment

@AndroidEntryPoint
class AssetsListFragment : BaseFragment<AssetsListBinding>() {

    override val viewModel: AssetsListViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.navigation.observe(viewLifecycleOwner) {
            navigate(it)
        }
    }
}