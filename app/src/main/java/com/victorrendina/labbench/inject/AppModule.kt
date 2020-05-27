package com.victorrendina.labbench.inject

import com.victorrendina.labbench.LabApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: LabApplication) = application.applicationContext

}