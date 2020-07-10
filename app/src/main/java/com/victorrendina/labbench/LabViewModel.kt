package com.victorrendina.labbench

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistry
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * Base view model class.
 */
abstract class LabViewModel<S : LabViewState, M: Any>(initialState: S) : ViewModel(), SavedStateRegistry.SavedStateProvider {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> get() = _state

    private val _messages = Channel<M>(Channel.UNLIMITED)
    val messages get() = _messages.consumeAsFlow()

    final override fun saveState(): Bundle {
        return _state.value.saveState()
    }

    protected fun updateState(reducer: S.() -> S) {
        synchronized(_state) {
            _state.value = _state.value.reducer()
        }
    }

    protected fun sendMessage(message: M) {
        _messages.offer(message)
    }
}
