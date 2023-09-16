package com.mangatalabs.sofatime

import android.app.Application
import com.mangatalabs.core_ui.di.coreUIModule
import com.mangatalabs.sofatime.di.appModule
import com.mangatalabs.tvshow_data.di.remoteModule
import com.mangatalabs.tvshow_presentation.di.tvShowModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class SofaTimeApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger()
            androidContext(this@SofaTimeApp)
            modules(appModule)
            modules(remoteModule)
            modules(tvShowModule)
            modules(coreUIModule)
        }
    }
}