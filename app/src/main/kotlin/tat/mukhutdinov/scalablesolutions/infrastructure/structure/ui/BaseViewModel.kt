package tat.mukhutdinov.scalablesolutions.infrastructure.structure.ui

import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel(), CoroutineScope {

    private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        handleCoroutineException(context, throwable)
    }

    override val coroutineContext: CoroutineContext = SupervisorJob() + kotlinx.coroutines.Dispatchers.IO + exceptionHandler

    val arguments by lazy { savedStateHandle.get<Bundle>(BUNDLE_ARGS) }

    open fun handleCoroutineException(context: CoroutineContext, throwable: Throwable) {
        Timber.e(throwable)
    }

    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }

    override fun onCleared() {
        super.onCleared()

        cancel()
    }

    companion object {
        const val BUNDLE_ARGS = "bundle_args"
    }
}