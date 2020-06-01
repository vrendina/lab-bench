package com.victorrendina.labbench.inject

import android.os.StrictMode
import dagger.Module
import dagger.Provides

@Module
class BuildTypeModule {

    @Provides
    fun provideThreadPolicy(): StrictMode.ThreadPolicy {
        return StrictMode.ThreadPolicy.LAX
    }

    @Provides
    fun provideVmPolicy(): StrictMode.VmPolicy {
        return StrictMode.VmPolicy.LAX
    }

    // Use different log writers in production
//    @Singleton
//    @Provides
//    @IntoSet
//    fun bindLogcatWriter(): LogWriter {
//        return LogcatWriter(Logger.Priority.DEBUG)
//    }
}
