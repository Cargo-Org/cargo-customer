package com.example.cargo_customer.app

import android.app.Application
import com.cargo.customer.shared.di.koinModule
import com.example.cargo_customer.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class CargoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CargoApplication)
            androidLogger()
            modules(koinModule)
        }
    }
}