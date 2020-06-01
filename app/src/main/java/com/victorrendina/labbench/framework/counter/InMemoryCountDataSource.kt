package com.victorrendina.labbench.framework.counter

import com.victorrendina.core.Fail
import com.victorrendina.core.Result
import com.victorrendina.core.Success
import com.victorrendina.data.counter.CountDataSource
import com.victorrendina.data.counter.CountNotSavedException
import com.victorrendina.domain.model.Count
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryCountDataSource @Inject constructor() : CountDataSource {

    private var store: Count? = null

    override suspend fun saveCount(count: Count) {
        store = count
    }

    override suspend fun retrieveCount(): Result<Count> {
        val count = store
        return if (count != null) {
            Success(count)
        } else {
            Fail(CountNotSavedException())
        }
    }
}
