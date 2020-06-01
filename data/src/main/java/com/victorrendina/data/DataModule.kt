package com.victorrendina.data

import com.victorrendina.data.counter.CountDataRepository
import com.victorrendina.domain.repository.CountRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bind(repo: CountDataRepository): CountRepository
}
