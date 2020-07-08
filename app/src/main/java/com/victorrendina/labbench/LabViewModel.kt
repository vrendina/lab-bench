package com.victorrendina.labbench

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Base view model class.
 */
abstract class LabViewModel<S : LabViewState>(initialState: S) : ViewModel(), SavedStateRegistry.SavedStateProvider {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state

    // TODO Add message passing method?

    final override fun saveState(): Bundle {
        return _state.value.saveState()
    }

    fun updateState(reducer: S.() -> S) {
        synchronized(_state) {
            _state.value = _state.value.reducer()
        }
    }
}
