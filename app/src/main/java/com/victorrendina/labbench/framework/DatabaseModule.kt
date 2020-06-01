package com.victorrendina.labbench.framework

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): LabDatabase {
        return Room.databaseBuilder(context, LabDatabase::class.java, "labbench.db").build()
    }

    @Provides
    fun provideCountDao(db: LabDatabase) = db.countDao()
}
