package tat.mukhutdinov.cryptostock.infrastructure.structure.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import tat.mukhutdinov.android.utils.autoCleared
import tat.mukhutdinov.cryptostock.BR
import tat.mukhutdinov.cryptostock.infrastructure.structure.ui.BaseViewModel.Companion.BUNDLE_NAV_ARG
import timber.log.Timber
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<ViewBinding : ViewDataBinding> : Fragment() {

    abstract val viewModel: BaseViewModel

    var viewBinding: ViewBinding by autoCleared()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewBindingClass: Class<ViewBinding> = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<ViewBinding>

        viewBinding = viewBindingClass.getMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
            .invoke(null, inflater, container, false) as ViewBinding

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.setVariable(BR.bindings, viewModel)

        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    open fun navigate(directions: NavDirections) {
        // Показ диалогов через navigation падает при вызове на бекграунд потоке
        activity?.runOnUiThread {
            try {
                findNavController().navigate(directions)
            } catch (exception: IllegalArgumentException) {
                Timber.w(exception)
            }
        }
    }

    open fun navigateUp() {
        findNavController().navigateUp()
    }

    override fun setArguments(args: Bundle?) {
        val navArgs = args?.let {
            Bundle(it).apply {
                putBundle(BUNDLE_NAV_ARG, args)
            }
        }

        super.setArguments(navArgs)
    }
}