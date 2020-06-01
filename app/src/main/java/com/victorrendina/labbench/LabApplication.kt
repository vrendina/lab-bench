package com.victorrendina.labbench

import android.app.Application
import android.os.StrictMode
import com.victorrendina.labbench.inject.AppComponent
import com.victorrendina.labbench.inject.DaggerAppComponent
import javax.inject.Inject

class LabApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    @Inject
    lateinit var threadPolicy: StrictMode.ThreadPolicy

    @Inject
    lateinit var vmPolicy: StrictMode.VmPolicy

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)

        StrictMode.setThreadPolicy(threadPolicy)
        StrictMode.setVmPolicy(vmPolicy)
    }
}
