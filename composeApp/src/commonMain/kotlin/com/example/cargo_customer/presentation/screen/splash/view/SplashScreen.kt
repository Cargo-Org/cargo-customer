package com.example.cargo_customer.presentation.screen.splash.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.cargo
import cargo_customer.composeapp.generated.resources.splash_slogan
import com.example.cargo_customer.presentation.base.ObserveAsEffect
import com.example.cargo_customer.presentation.component.AnimatedCargoLogo
import com.example.cargo_customer.presentation.component.AnimatedProgressBar
import com.example.cargo_customer.presentation.component.AnimatedThreeDotsBar
import com.example.cargo_customer.presentation.navigation.LocalNavController
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.screen.splash.view.SplashAnim.SPLASH_DURATION_MS
import com.example.cargo_customer.presentation.screen.splash.viewmodel.SplashEffect
import com.example.cargo_customer.presentation.screen.splash.viewmodel.SplashViewModel
import com.example.cargo_customer.presentation.theme.CargoTheme
import com.example.cargo_customer.presentation.theme.CargoTheme.dimens
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel()
) {
    val navController = LocalNavController.current

    LaunchedEffect(Unit) {
        delay(SPLASH_DURATION_MS)
        viewModel.determineNextDestination()
    }

    ObserveAsEffect(viewModel.effect) { effects ->
        when (effects) {
            SplashEffect.NavigateToOnboarding -> {
                navController.navigate(Route.OnboardingRoute) {
                    popUpTo(Route.SplashRoute) { inclusive = true }
                }
            }
            SplashEffect.NavigateToLogin -> {
                navController.navigate(Route.LoginRoute) {
                    popUpTo(Route.SplashRoute) { inclusive = true }
                }
            }
            SplashEffect.NavigateToHome -> {
                navController.navigate(Route.HomeRoute) {
                    popUpTo(Route.SplashRoute) { inclusive = true }
                }
            }
        }
    }

    SplashScreenContent()
}

@Composable
private fun SplashScreenContent() {

    var logoOffsetX by remember { mutableStateOf(SplashAnim.LOGO_START_X) }

    var cargoOffset by remember { mutableStateOf(SplashAnim.TEXT_START_OFFSET) }
    var cargoAlpha by remember { mutableStateOf(SplashAnim.TITLE_ALPHA_START) }

    var sloganOffset by remember { mutableStateOf(SplashAnim.TEXT_START_OFFSET) }
    var sloganAlpha by remember { mutableStateOf(SplashAnim.TITLE_ALPHA_START) }

    var progressAlpha by remember { mutableStateOf(0f) }
    var dotsAlpha by remember { mutableStateOf(0f) }
    var progress by remember { mutableStateOf(0f) }
    var dots by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        logoOffsetX = 0f
        delay(SplashAnim.LOGO_SLIDE_MS.toLong())

        cargoOffset = 0f
        cargoAlpha = 1f
        delay(SplashAnim.TEXT_SLIDE_MS.toLong())

        sloganOffset = 0f
        sloganAlpha = 1f
        delay(SplashAnim.TEXT_SLIDE_MS.toLong())

        progressAlpha = 1f
        dotsAlpha = 1f
        progress = SplashAnim.PROGRESS_MAX

        repeat(SplashAnim.DOTS_COUNT) {
            dots++
            delay(SplashAnim.DOT_STEP_MS)
        }
    }

    val animatedLogoOffsetX by animateFloatAsState(
        targetValue = logoOffsetX,
        animationSpec = tween(SplashAnim.LOGO_SLIDE_MS),
        label = "logo offset"
    )

    val cargoTextOffset by animateFloatAsState(
        targetValue = cargoOffset,
        animationSpec = tween(SplashAnim.TEXT_SLIDE_MS),
        label = "cargo offset"
    )

    val cargoTextAlpha by animateFloatAsState(
        targetValue = cargoAlpha,
        animationSpec = tween(SplashAnim.TEXT_SLIDE_MS),
        label = "cargo alpha"
    )

    val sloganTextOffset by animateFloatAsState(
        targetValue = sloganOffset,
        animationSpec = tween(SplashAnim.TEXT_SLIDE_MS),
        label = "slogan offset"
    )

    val sloganTextAlpha by animateFloatAsState(
        targetValue = sloganAlpha,
        animationSpec = tween(SplashAnim.TEXT_SLIDE_MS),
        label = "slogan alpha"
    )

    val progressAlphaAnim by animateFloatAsState(
        targetValue = progressAlpha,
        animationSpec = tween(SplashAnim.PROGRESS_FADE_MS),
        label = "progress alpha"
    )

    val dotsAlphaAnim by animateFloatAsState(
        targetValue = dotsAlpha,
        animationSpec = tween(SplashAnim.DOTS_FADE_MS),
        label = "dots alpha"
    )

    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween((SplashAnim.DOT_STEP_MS * SplashAnim.DOTS_COUNT).toInt()),
        label = "progress fill"
    )

    Box(
        modifier = Modifier.fillMaxSize().background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        CargoTheme.colorScheme.background.copy(alpha = SplashAnim.BACKGROUND_ALPHA),
                        CargoTheme.colorScheme.background,
                    )
                )
            ).padding(vertical = dimens.spacing.colossal)
    ) {

        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(
                    dimens.spacing.md, Alignment.CenterVertically
                ), horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.border(
                            width = 2.dp,
                            color = CargoTheme.colorScheme.outline,
                            shape = RoundedCornerShape(dimens.spacing.md)
                        ).padding(dimens.spacing.xxl).clipToBounds()
                ) {
                    AnimatedCargoLogo(
                        modifier = Modifier.size(dimens.spacing.colossal)
                            .offset(x = animatedLogoOffsetX.dp),
                    )
                }

                Spacer(modifier = Modifier.height(dimens.spacing.md))

                Text(
                    stringResource(Res.string.cargo),
                    modifier = Modifier.offset(y = cargoTextOffset.dp).alpha(cargoTextAlpha),
                    style = CargoTheme.typography.headlineLarge.copy(
                        color = CargoTheme.colorScheme.onBackground,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 3.sp
                    )
                )

                Text(
                    stringResource(Res.string.splash_slogan),
                    modifier = Modifier.offset(y = sloganTextOffset.dp).alpha(sloganTextAlpha),
                    style = CargoTheme.typography.bodySmall.copy(
                        color = CargoTheme.colorScheme.outline
                    )
                )

                Spacer(modifier = Modifier.height(dimens.spacing.colossal))

                AnimatedProgressBar(
                    modifier = Modifier.fillMaxWidth(0.7f).height(dimens.spacing.sm + 2.dp)
                        .alpha(progressAlphaAnim), progress = animatedProgress
                )
            }

            AnimatedThreeDotsBar(
                modifier = Modifier.fillMaxWidth(0.5f).alpha(dotsAlphaAnim), progress = dots
            )
        }
    }
}

private object SplashAnim {

    const val LOGO_SLIDE_MS = 700
    const val LOGO_START_X = -150f

    const val TEXT_SLIDE_MS = 500
    const val TEXT_START_OFFSET = 50f

    const val TITLE_ALPHA_START = 0f
    const val SPLASH_DURATION_MS = 3700L

    const val PROGRESS_FADE_MS = 400
    const val PROGRESS_MAX = 1f

    const val DOTS_FADE_MS = 300
    const val DOT_STEP_MS = 300L
    const val DOTS_COUNT = 5

    const val BACKGROUND_ALPHA = 0.95f
}

@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SplashScreenContent()
}