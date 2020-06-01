package com.victorrendina.labbench.framework.counter

import com.victorrendina.data.Local
import com.victorrendina.data.Remote
import com.victorrendina.data.counter.CountDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class CounterModule {

    @Binds
    @Local
    abstract fun bindLocal(source: PreferencesCountDataSource): CountDataSource

    @Binds
    @Remote
    abstract fun bindRemote(source: InMemoryCountDataSource): CountDataSource
}
