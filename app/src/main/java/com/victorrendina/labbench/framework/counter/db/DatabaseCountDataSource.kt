package com.victorrendina.labbench.framework.counter.db

import com.victorrendina.core.Result
import com.victorrendina.core.Success
import com.victorrendina.data.counter.CountDataSource
import com.victorrendina.domain.model.Count
import javax.inject.Inject

class DatabaseCountDataSource @Inject constructor(
    private val dao: CountDao
// TODO Need a mapper
) : CountDataSource {

    override suspend fun saveCount(count: Count) {
        dao.updateCount(CountEntity(0, count.count, count.created, count.updated))
    }

    override suspend fun retrieveCount(): Result<Count> {
        // TODO Is this going to crash if nothing is saved? What happens here?
        val entity = dao.getCount()
        return Success(Count(entity.count, entity.created, entity.updated))
    }
}
