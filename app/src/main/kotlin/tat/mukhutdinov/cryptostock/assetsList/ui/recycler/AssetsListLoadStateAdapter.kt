package tat.mukhutdinov.cryptostock.assetsList.ui.recycler

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import tat.mukhutdinov.cryptostock.assetsList.ui.boundary.AssetsListBindings

class AssetsListLoadStateAdapter(
    private val bindings: AssetsListBindings
) : LoadStateAdapter<AssetsListLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: AssetsListLoadStateViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): AssetsListLoadStateViewHolder =
        AssetsListLoadStateViewHolder.create(parent, bindings)

    override fun onViewRecycled(holder: AssetsListLoadStateViewHolder) {
        super.onViewRecycled(holder)

        holder.onViewRecycled()
    }
}
