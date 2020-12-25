package tat.mukhutdinov.scalablesolutions

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tat.mukhutdinov.scalablesolutions.infrastructure.di.Modules

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@App)

            modules(Modules.modules)
        }
    }
}