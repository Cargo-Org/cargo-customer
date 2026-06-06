package com.example.cargo_customer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargo_customer.presentation.navigation.callbacks.OnboardingNavigationCallbacks
import com.example.cargo_customer.presentation.navigation.callbacks.SplashNavigationCallbacks
import com.example.cargo_customer.presentation.screen.login.LoginScreen
import com.example.cargo_customer.presentation.screen.onboarding.view.OnboardingScreen
import com.example.cargo_customer.presentation.screen.register.view.RegisterScreen
import com.example.cargo_customer.presentation.screen.splash.SplashScreen
import com.example.cargo_customer.presentation.screen.upload_docs.UploadDocumentsScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = Route.SplashRoute, modifier = modifier
    ) {
        composable<Route.SplashRoute> {
            SplashScreen(
                navigationCallbacks = SplashNavigationCallbacks(
                    onNavigateToOnboarding = { navController.navigate(Route.OnboardingRoute){
                        popUpTo(Route.SplashRoute) { inclusive = true }
                    } },
                    onNavigateToLogin = { navController.navigate(Route.LoginRoute){
                        popUpTo(Route.SplashRoute) { inclusive = true }
                    } },
                    onNavigateToHome = { /* TODO to navigate to Home*/ })
            )
        }
        composable<Route.OnboardingRoute> {
            OnboardingScreen(
                navigationCallbacks = OnboardingNavigationCallbacks(
                    onNavigateToLogin = { navController.navigate(Route.LoginRoute){
                        popUpTo(Route.OnboardingRoute) { inclusive = true }
                    } })
            )
        }
        composable<Route.LoginRoute> { LoginScreen() }
        composable<Route.RegisterRoute> { RegisterScreen() }
        composable<Route.UploadDocs> { UploadDocumentsScreen() }
    }
}