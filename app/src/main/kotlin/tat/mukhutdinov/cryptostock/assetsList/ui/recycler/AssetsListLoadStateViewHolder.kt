package tat.mukhutdinov.cryptostock.assetsList.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.cryptostock.R
import tat.mukhutdinov.cryptostock.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.cryptostock.databinding.AssetsListLoadStateBinding

class AssetsListLoadStateViewHolder(
    private val viewBinding: AssetsListLoadStateBinding,
    private val bindings: AssetsListBindings
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindTo(loadState: LoadState) {
        viewBinding.bindings = bindings

        if (loadState is LoadState.Error) {
            viewBinding.error.message.text = loadState.error.localizedMessage
        }

        viewBinding.error.container.isVisible = loadState !is LoadState.Loading

        if (loadState is LoadState.Loading) {
            viewBinding.progress.show()
        } else {
            viewBinding.progress.hide()
        }

        viewBinding.executePendingBindings()
    }

    fun onViewRecycled() {
        viewBinding.unbind()
    }

    companion object {

        fun create(parent: ViewGroup, bindings: AssetsListBindings): AssetsListLoadStateViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewDataBinding: AssetsListLoadStateBinding = DataBindingUtil.inflate(layoutInflater, R.layout.assets_list_load_state, parent, false)

            return AssetsListLoadStateViewHolder(viewDataBinding, bindings)
        }
    }
}
