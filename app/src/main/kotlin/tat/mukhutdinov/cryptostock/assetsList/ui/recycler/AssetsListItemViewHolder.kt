package tat.mukhutdinov.cryptostock.assetsList.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import tat.mukhutdinov.cryptostock.R
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.assetsList.ui.boundary.AssetsListBindings
import tat.mukhutdinov.cryptostock.databinding.AssetsListItemBinding

class AssetsListItemViewHolder(
    private val viewBinding: AssetsListItemBinding,
    private val bindings: AssetsListBindings
) : RecyclerView.ViewHolder(viewBinding.root) {

    fun bindTo(asset: Asset) {
        viewBinding.asset = asset
        viewBinding.bindings = bindings
        viewBinding.executePendingBindings()
    }

    fun onViewRecycled() {
        viewBinding.unbind()
    }

    companion object {

        fun create(parent: ViewGroup, bindings: AssetsListBindings): AssetsListItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val viewDataBinding: AssetsListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.assets_list_item, parent, false)

            return AssetsListItemViewHolder(viewDataBinding, bindings)
        }
    }
}