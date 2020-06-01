package com.victorrendina.domain.interactor

import com.victorrendina.core.AppDispatchers
import com.victorrendina.core.Success
import com.victorrendina.domain.model.Count
import com.victorrendina.domain.repository.CountRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveCount @Inject constructor(
    private val repository: CountRepository,
    private val retrieveCount: RetrieveCount,
    private val dispatchers: AppDispatchers
) {

    suspend operator fun invoke(value: Int) = withContext(dispatchers.io) {
        val now = System.currentTimeMillis()
        val existingCount = retrieveCount()
        if (existingCount is Success) {
            // Only save the count if it has changed from the value we had stored
            if (existingCount.value.count != value) {
                repository.saveCount(existingCount.value.copy(count = value, updated = now))
            }
        } else {
            repository.saveCount(Count(value, now))
        }
    }
}
