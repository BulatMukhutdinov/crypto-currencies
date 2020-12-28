package tat.mukhutdinov.scalablesolutions.asset.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import tat.mukhutdinov.scalablesolutions.asset.domain.model.OfficialLink
import tat.mukhutdinov.scalablesolutions.databinding.AssetBinding
import tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui.BaseFragment

@AndroidEntryPoint
class AssetFragment : BaseFragment<AssetBinding>() {

    override val viewModel: AssetViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.details.movementMethod = LinkMovementMethod.getInstance();

        viewModel.asset.officialLinks.forEach {
            addLink(it)
        }
    }

    private fun addLink(officialLink: OfficialLink) {
        val chip = Chip(context).apply {
            text = officialLink.name
            setOnClickListener { openLink(officialLink.link) }
        }

        viewBinding.links.addView(chip)
    }

    private fun openLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        activity?.packageManager?.let {
            if (intent.resolveActivity(it) != null) {
                startActivity(intent)
            }
        }
    }
}