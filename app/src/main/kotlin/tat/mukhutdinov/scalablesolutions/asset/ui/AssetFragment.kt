package tat.mukhutdinov.scalablesolutions.asset.ui

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tat.mukhutdinov.scalablesolutions.databinding.AssetBinding
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseFragment

@AndroidEntryPoint
class AssetFragment : BaseFragment<AssetBinding>() {

    override val viewModel: AssetViewModel by viewModels()


}