package com.victorrendina.labbench.inject

import com.victorrendina.data.DataModule
import com.victorrendina.labbench.LabApplication
import com.victorrendina.labbench.experiments.counter.CounterFragment
import com.victorrendina.labbench.framework.FrameworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppAssistedModule::class,
        BuildTypeModule::class,
        DataModule::class,
        FrameworkModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: LabApplication): AppComponent
    }

    fun inject(application: LabApplication)
    fun inject(counterFragment: CounterFragment)
}
