package com.victorrendina.domain.interactor

import com.victorrendina.core.AppDispatchers
import com.victorrendina.core.Result
import com.victorrendina.domain.model.Count
import com.victorrendina.domain.repository.CountRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrieveCount @Inject constructor(
    private val repository: CountRepository,
    private val dispatchers: AppDispatchers
) {

    suspend operator fun invoke(): Result<Count> = withContext(dispatchers.io) {
        repository.retrieveCount()
    }
}
