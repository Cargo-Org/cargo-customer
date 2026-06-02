package com.example.cargo_customer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cargo_customer.presentation.screen.login.LoginScreen
import com.example.cargo_customer.presentation.screen.register.RegisterScreen
import com.example.cargo_customer.presentation.screen.splash.SplashScreen
import com.example.cargo_customer.presentation.screen.upload_docs.UploadDocumentsScreen
import com.example.cargo_customer.presentation.screen.verify_email.EmailVerificationScreen


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
            composable<Route.LoginRoute> { LoginScreen() }
            composable<Route.RegisterRoute> { RegisterScreen() }
            composable<Route.VerifyEmail> { EmailVerificationScreen() }
            composable<Route.UploadDocs> { UploadDocumentsScreen() }
        }
    }
}
val LocalNavController = compositionLocalOf<NavController> {
    error("NavController not provided")
}