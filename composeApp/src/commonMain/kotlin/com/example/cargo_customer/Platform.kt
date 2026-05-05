package com.example.cargo_customer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform