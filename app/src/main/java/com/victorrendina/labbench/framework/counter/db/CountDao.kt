package com.victorrendina.labbench.framework.counter.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountDao {

    @Query("SELECT * FROM count LIMIT 1")
    suspend fun getCount(): CountEntity

    @Query("SELECT * FROM count LIMIT 1")
    fun observeCount(): Flow<CountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCount(countEntity: CountEntity)
}
