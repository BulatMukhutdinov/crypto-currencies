package tat.mukhutdinov.cryptostock.assetsList.ui.recycler

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import tat.mukhutdinov.cryptostock.asset.domain.model.Asset
import tat.mukhutdinov.cryptostock.assetsList.ui.boundary.AssetsListBindings

class AssetsListAdapter(
    private val bindings: AssetsListBindings
) : PagingDataAdapter<Asset, AssetsListItemViewHolder>(assetComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetsListItemViewHolder =
        AssetsListItemViewHolder.create(parent, bindings)

    override fun onBindViewHolder(holder: AssetsListItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindTo(it)
        }
    }

    override fun onViewRecycled(holder: AssetsListItemViewHolder) {
        super.onViewRecycled(holder)

        holder.onViewRecycled()
    }

    companion object {
        private val assetComparator = object : DiffUtil.ItemCallback<Asset>() {

            override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean =
                oldItem == newItem
        }
    }
}