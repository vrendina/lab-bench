package com.victorrendina.labbench

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

/**
 * Base fragment class.
 */
abstract class LabFragment : Fragment() {

    /**
     * Observe flow using the view lifecycle.
     */
    protected fun <T> Flow<T>.observe(action: suspend (T) -> Unit) {
        onEach { value ->
            action(value)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}
