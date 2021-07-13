package de.p72b.redandroid.gothouses

import android.annotation.SuppressLint
import android.app.Application
import de.p72b.redandroid.gothouses.DependencyGraph.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@App)
            modules(get())
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Application
    }
}