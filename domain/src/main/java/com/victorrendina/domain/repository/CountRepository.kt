package com.victorrendina.domain.repository

import com.victorrendina.core.Result
import com.victorrendina.domain.model.Count

interface CountRepository {

    suspend fun saveCount(count: Count)

    suspend fun retrieveCount(): Result<Count>
}
