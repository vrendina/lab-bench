package com.victorrendina.data.counter

import com.victorrendina.core.Result
import com.victorrendina.domain.model.Count

interface CountDataSource {

    suspend fun saveCount(count: Count)

    suspend fun retrieveCount(): Result<Count>
}
