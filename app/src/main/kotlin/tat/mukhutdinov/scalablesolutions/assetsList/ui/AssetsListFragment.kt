package tat.mukhutdinov.scalablesolutions.assetsList.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import tat.mukhutdinov.android.utils.autoCleared
import tat.mukhutdinov.scalablesolutions.assetsList.ui.recycler.AssetsListAdapter
import tat.mukhutdinov.scalablesolutions.databinding.AssetsListBinding
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseFragment

@AndroidEntryPoint
class AssetsListFragment : BaseFragment<AssetsListBinding>() {

    override val viewModel: AssetsListViewModel by viewModels()

    private var adapter: AssetsListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()

        setupAdapters()
    }

    private fun setupNavigation() {
        viewModel.navigation.observe(viewLifecycleOwner) {
            navigate(it)
        }
    }

    private fun setupAdapters() {
        adapter = viewModel.createAssetsListAdapter()

        viewBinding.assets.adapter = adapter.withLoadStateHeaderAndFooter(
            header = viewModel.createAssetsListLoadStateAdapter(),
            footer = viewModel.createAssetsListLoadStateAdapter()
        )

        viewBinding.assets.setHasFixedSize(true)

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            viewBinding.assets.isVisible = loadState.source.refresh is LoadState.NotLoading

            // Show loading during initial load or refresh.
            if (loadState.source.refresh is LoadState.Loading) {
                viewBinding.progress.show()
            } else {
                viewBinding.progress.hide()
            }

            // Show the retry state if initial load or refresh fails.
            val refresh = loadState.source.refresh

            viewBinding.retry.isVisible = refresh is LoadState.Error
            viewBinding.message.isVisible = refresh is LoadState.Error

            if (refresh is LoadState.Error) {
                viewBinding.message.text = refresh.error.localizedMessage
            }
        }

        viewModel.assets.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}