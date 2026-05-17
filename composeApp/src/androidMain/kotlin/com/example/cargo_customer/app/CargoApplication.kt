package com.example.cargo_customer.app

import android.app.Application
import com.example.cargo_customer.initKoin
import org.koin.android.ext.koin.androidContext


class CargoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@CargoApplication)
        }
    }
}