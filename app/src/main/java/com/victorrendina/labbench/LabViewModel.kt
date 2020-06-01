package com.victorrendina.labbench

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * Base view model class.
 */
abstract class LabViewModel<S : LabViewState>(initialState: S) : ViewModel(), SavedStateRegistry.SavedStateProvider {

    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<S>
        get() = _state

    final override fun saveState(): Bundle {
        return _state.value.saveState()
    }

    fun updateState(reducer: S.() -> S) {
        _state.value = _state.value.reducer()
    }
}
