package com.victorrendina.labbench.framework.counter.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "count")
class CountEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "count") val count: Int,
    @ColumnInfo(name = "created") val created: Long,
    @ColumnInfo(name = "updated") val updated: Long
)
