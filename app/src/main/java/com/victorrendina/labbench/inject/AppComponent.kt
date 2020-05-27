package com.victorrendina.labbench.inject

import com.victorrendina.labbench.LabApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        AppAssistedModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: LabApplication): AppComponent
    }

//    fun inject(counterFragment: CounterFragment)

}