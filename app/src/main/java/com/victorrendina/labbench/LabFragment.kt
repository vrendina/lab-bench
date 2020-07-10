package com.victorrendina.labbench

import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Base fragment class.
 */
abstract class LabFragment : Fragment() {

    /**
     * Collect flow when the lifecycle of the owner is at least a minimum state. If the lifecycle leaves
     * the minimum state the collection of the flow wll
     *
     * @param owner The lifecycle owner
     * @param minState The desired minimum state to run the [collector].
     * @param collector The block to run when the lifecycle is at least in [minState].
     */
    inline fun <T> Flow<T>.collectLifecycle(
        owner: LifecycleOwner = viewLifecycleOwner,
        minState: Lifecycle.State = Lifecycle.State.STARTED,
        crossinline collector: suspend (T) -> Unit
    ): Job {
        return owner.lifecycleScope.launch {
            owner.lifecycle.whenStateAtLeast(minState) {
                collect(collector)
            }
        }
    }


}
