package com.example.cargo_customer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargo_customer.presentation.login.LoginScreen
import com.example.cargo_customer.presentation.screen.onboarding.view.OnboardingScreen
import com.example.cargo_customer.presentation.screen.splash.SplashScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: Route
){
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier
        ) {
            composable<Route.SplashRoute> { SplashScreen() }
            composable<Route.OnboardingRoute> { OnboardingScreen() }
            composable<Route.LoginRoute> { LoginScreen() }
        }
    }
}
val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}