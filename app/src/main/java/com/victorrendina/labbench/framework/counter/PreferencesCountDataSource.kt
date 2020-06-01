package com.victorrendina.labbench.framework.counter

import android.content.SharedPreferences
import com.victorrendina.core.Fail
import com.victorrendina.core.Result
import com.victorrendina.core.Success
import com.victorrendina.data.counter.CountDataSource
import com.victorrendina.data.counter.CountNotSavedException
import com.victorrendina.domain.model.Count
import javax.inject.Inject

class PreferencesCountDataSource @Inject constructor(
    private val preferences: SharedPreferences
) : CountDataSource {
    override suspend fun saveCount(count: Count) {
        preferences.edit()
            .putInt(COUNT_KEY, count.count)
            .putLong(CREATED_KEY, count.created)
            .putLong(UPDATED_KEY, count.updated)
            .apply()
    }

    override suspend fun retrieveCount(): Result<Count> {
        val created = preferences.getLong(CREATED_KEY, -1)
        if (created >= 0) {
            val count = preferences.getInt(COUNT_KEY, 0)
            val updated = preferences.getLong(UPDATED_KEY, -1)
            return Success(Count(count, created, updated))
        }
        return Fail(CountNotSavedException())
    }

    companion object {
        private const val COUNT_KEY = "count_value"
        private const val CREATED_KEY = "count_created"
        private const val UPDATED_KEY = "count_updated"
    }
}
