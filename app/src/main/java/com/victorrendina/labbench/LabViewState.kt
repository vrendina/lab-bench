package com.victorrendina.labbench

import android.os.Bundle

interface LabViewState {
    /**
     * Used to persist the state of the view through process death.
     */
    fun saveState(): Bundle = Bundle.EMPTY
}
