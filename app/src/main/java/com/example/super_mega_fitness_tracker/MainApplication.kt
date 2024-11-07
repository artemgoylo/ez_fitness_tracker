package com.example.super_mega_fitness_tracker

import android.app.Application
import com.example.super_mega_fitness_tracker.di.appModule
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}