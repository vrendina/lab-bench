package com.victorrendina.labbench.experiments.counter

import androidx.lifecycle.viewModelScope
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import com.victorrendina.core.Logger
import com.victorrendina.core.Success
import com.victorrendina.domain.interactor.RetrieveCount
import com.victorrendina.domain.interactor.SaveCount
import com.victorrendina.labbench.LabViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class CounterViewModel @AssistedInject constructor(
    @Assisted initialState: CounterViewState,
    private val saveCount: SaveCount,
    private val retrieveCount: RetrieveCount,
    private val log: Logger
) : LabViewModel<CounterViewState>(initialState) {

    private val _messages = Channel<Any>(Channel.UNLIMITED)
    val messages get() = _messages.consumeAsFlow()

    init {
        if (!initialState.restored) {
            viewModelScope.launch {
                val storedCount = retrieveCount()
                if (storedCount is Success) {
                    updateState { copy(count = storedCount.value.count, loading = false) }
                } else {
                    // Handle count fetch errors
                    updateState { copy(loading = false) }
                }
            }
        }
    }

    fun increment() {
        updateState {
            _messages.offer("Updating count to ${count + 1}")
            copy(count = count + 1)
        }
    }

    fun decrement() {
        updateState {
            copy(count = count - 1)
        }
    }

    fun save() {
        val count = state.value.count
        viewModelScope.launch {
            saveCount(count)
        }
    }

    @AssistedInject.Factory
    interface Factory {
        fun create(initialState: CounterViewState): CounterViewModel
    }

    companion object {
        private val TAG = CounterViewModel::class.java.simpleName
    }
}
