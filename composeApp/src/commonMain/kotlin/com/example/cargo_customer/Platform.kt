package com.example.cargo_customer

import org.koin.dsl.KoinAppDeclaration

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun initKoin(koinDeclaration: KoinAppDeclaration? = null)