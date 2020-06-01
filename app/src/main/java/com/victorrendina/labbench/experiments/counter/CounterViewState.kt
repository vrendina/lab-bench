package com.victorrendina.labbench.experiments.counter

import android.os.Bundle
import com.victorrendina.labbench.LabViewState

data class CounterViewState(
    val count: Int,
    val restored: Boolean, // If the value was restored from a saved state
    val loading: Boolean
) : LabViewState {

    override fun saveState(): Bundle {
        return Bundle().apply {
            putInt(COUNT_KEY, count)
        }
    }

    companion object {
        fun create(savedState: Bundle?, initialCount: Int): CounterViewState {
            val count = savedState?.getInt(COUNT_KEY, initialCount) ?: initialCount
            val restored = savedState != null
            return CounterViewState(count, restored, !restored)
        }

        private const val COUNT_KEY = "count"
    }
}
