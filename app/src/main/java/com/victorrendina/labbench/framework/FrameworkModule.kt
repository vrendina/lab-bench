package com.victorrendina.labbench.framework

import com.victorrendina.core.LogWriter
import com.victorrendina.labbench.framework.counter.CounterModule
import dagger.Module
import dagger.Provides
import dagger.multibindings.ElementsIntoSet

@Module(
    includes = [
        CounterModule::class,
        DatabaseModule::class
    ]
)
class FrameworkModule {

    @Provides
    @ElementsIntoSet
    fun provideLogWriters(): Set<LogWriter> = emptySet()
}
