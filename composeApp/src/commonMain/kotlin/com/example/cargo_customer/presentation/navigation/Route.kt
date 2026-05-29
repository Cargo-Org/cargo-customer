package com.example.cargo_customer.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    /* if screen will take argument example :
    @Serializable
    data class MapRoute(val isFromSetting : Boolean = false) : Route

    if screen not have argument example:
    @Serializable
    object FavoriteRoute : Route
    * */

    @Serializable
    object LoginRoute : Route

    @Serializable
    object RegisterRoute : Route
    @Serializable
    object SplashRoute : Route

    @Serializable
    object OnboardingRoute : Route
}