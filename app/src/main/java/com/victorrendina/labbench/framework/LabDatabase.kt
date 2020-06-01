package com.victorrendina.labbench.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.victorrendina.labbench.framework.counter.db.CountDao
import com.victorrendina.labbench.framework.counter.db.CountEntity

@Database(
    entities = [CountEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LabDatabase : RoomDatabase() {

    abstract fun countDao(): CountDao
}
