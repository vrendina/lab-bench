package com.victorrendina.labbench.inject

import android.content.Context
import android.content.SharedPreferences
import com.victorrendina.core.AppDispatchers
import com.victorrendina.labbench.LabApplication
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: LabApplication): Context = application.applicationContext

    @Provides
    fun provideDispatchers(): AppDispatchers = AppDispatchers(Dispatchers.IO, Dispatchers.Default, Dispatchers.Main)

    @Singleton
    @Provides
    fun providePreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("global", Context.MODE_PRIVATE)
    }
}
