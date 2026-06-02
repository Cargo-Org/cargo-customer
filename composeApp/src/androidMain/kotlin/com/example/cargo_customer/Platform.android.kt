package com.example.cargo_customer

import android.os.Build
import com.cargo.customer.shared.di.androidModule
import com.example.cargo_customer.di.presentationModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun initKoin(koinDeclaration: KoinAppDeclaration?) {
    startKoin {
        androidLogger()
        koinDeclaration?.invoke(this)
        modules(androidModule + presentationModule)
    }
}