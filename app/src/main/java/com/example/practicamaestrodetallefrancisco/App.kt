package com.example.practicamaestrodetallefrancisco

import android.app.Application
import timber.log.Timber

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        // Con esto se puede ver si la app esta en produccion o en desarrollo (si esta en movil de usuario o el de desarrollo) servira para en cada caso hacer una cosa u otra
//        if (BuildConfig.DEBUG) {
//        } else {
//            Timber.plant(CrashReportingTree())
//        }

    }
}