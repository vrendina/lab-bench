package com.victorrendina.labbench.inject

import android.os.StrictMode
import com.victorrendina.core.LogWriter
import com.victorrendina.core.Logger
import com.victorrendina.labbench.framework.logging.LogcatWriter
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
class BuildTypeModule {

    @Provides
    fun provideThreadPolicy(): StrictMode.ThreadPolicy {
        return StrictMode.ThreadPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .penaltyDeath()
            .build()
    }

    @Provides
    fun provideVmPolicy(): StrictMode.VmPolicy {
        return StrictMode.VmPolicy.Builder()
            .detectAll()
            .penaltyLog()
            .build()
    }

    @Singleton
    @Provides
    @IntoSet
    fun bindLogcatWriter(): LogWriter {
        return LogcatWriter(Logger.Priority.DEBUG)
    }
}
