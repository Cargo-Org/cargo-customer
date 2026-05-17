package com.example.cargo_customer.app

import android.app.Application
import com.cargo.customer.shared.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class CargoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        println("Application")
        startKoin {
            androidContext(this@CargoApplication)
            modules(koinModule)
        }
    }
}