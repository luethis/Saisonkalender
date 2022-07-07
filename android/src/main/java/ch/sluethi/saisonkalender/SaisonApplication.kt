package ch.sluethi.saisonkalender

import android.app.Application
import ch.sluethi.saisonkalender.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SaisonApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@SaisonApplication)
            androidLogger()
            modules(appModule())
        }
    }
}