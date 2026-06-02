package com.example.cargo_customer

import com.cargo.customer.shared.di.iosModules
<<<<<<< HEAD
import com.example.cargo_customer.presentation.di.presentationModule
=======
import com.example.cargo_customer.di.presentationModule
>>>>>>> feature/auth-register
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun initKoin(koinDeclaration: KoinAppDeclaration?) {
    startKoin {
        koinDeclaration?.invoke(this)
        modules(iosModules + presentationModule)
    }
}