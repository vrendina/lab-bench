package com.victorrendina.data.counter

import com.victorrendina.core.Fail
import com.victorrendina.core.Logger
import com.victorrendina.core.Result
import com.victorrendina.core.Success
import com.victorrendina.data.Local
import com.victorrendina.data.Remote
import com.victorrendina.domain.model.Count
import com.victorrendina.domain.repository.CountRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class CountDataRepository @Inject constructor(
    @Local private val localStore: CountDataSource,
    @Remote private val remoteStore: CountDataSource,
    private val log: Logger
) : CountRepository {

    override suspend fun saveCount(count: Count) {
        log.d("Saving count $count")
        coroutineScope {
            launch {
                log.d("Saving count to local store")
                localStore.saveCount(count)
            }
            launch {
                log.d("Saving count to remote store")
                remoteStore.saveCount(count)
            }
        }
    }

    override suspend fun retrieveCount(): Result<Count> {
        val local = localStore.retrieveCount()
        if (local is Success && !local.value.isExpired()) {
            log.d("Count retrieved from local storage")
            return local
        }

        val remoteCount = remoteStore.retrieveCount()
        if (remoteCount is Success) {
            log.d("Count retrieved from remote storage, saving to local storage")
            localStore.saveCount(remoteCount.value)
            return remoteCount
        }

        if (local is Success) {
            log.d("Local value was expired, but remote value could not be found, using local")
            return local
        }

        log.d("Could not find count in local or remote stores")

        return Fail(CountNotSavedException())
    }

    private fun Count.isExpired(): Boolean {
        val lastUpdated = if (updated == -1L) created else updated
        return System.currentTimeMillis() - lastUpdated > MAX_AGE
    }

    companion object {
        private const val MAX_AGE = 1000 * 60 * 5 // 5 minutes
    }
}
