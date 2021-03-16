package edu.fer.porttown

import android.app.Application
import edu.fer.porttown.di.appModule
import edu.fer.porttown.di.netModule
import edu.fer.porttown.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PortTownApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PortTownApplication)

            modules(
                listOf(
                    appModule,
                    netModule,
                    repositoryModule
                )
            )
        }
    }
}