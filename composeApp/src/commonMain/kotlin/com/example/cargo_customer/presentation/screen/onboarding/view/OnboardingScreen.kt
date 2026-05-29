package com.example.cargo_customer.presentation.screen.onboarding.view

import OnboardingPage
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.get_started
import cargo_customer.composeapp.generated.resources.onboarding1
import cargo_customer.composeapp.generated.resources.onboarding2
import cargo_customer.composeapp.generated.resources.onboarding3
import cargo_customer.composeapp.generated.resources.onboardingOneSubtitle
import cargo_customer.composeapp.generated.resources.onboardingOneTitle
import cargo_customer.composeapp.generated.resources.onboardingThreeSubtitle
import cargo_customer.composeapp.generated.resources.onboardingThreeTitle
import cargo_customer.composeapp.generated.resources.onboardingTwoSubtitle
import cargo_customer.composeapp.generated.resources.onboardingTwoTitle
import com.example.cargo_customer.presentation.base.ObserveAsEffect
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.navigation.LocalNavController
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.screen.onboarding.viewmodel.OnboardingEffect
import com.example.cargo_customer.presentation.screen.onboarding.viewmodel.OnboardingViewModel
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingScreen(
    viewModel : OnboardingViewModel = koinViewModel(),
){
    val navController = LocalNavController.current

    ObserveAsEffect(viewModel.effect) { effect ->
        when (effect) {
            is OnboardingEffect.NavigateToLogin -> navController.navigate(Route.LoginRoute)
        }
    }

    OnboardingScreenContent(
        onGetStartedClick = viewModel::onGetStartedClick
    )
}

@Composable
private fun OnboardingScreenContent(
    onGetStartedClick: () -> Unit ,
    modifier: Modifier = Modifier
){
    val pages = remember { getPages() }
    val pagerState = rememberPagerState { pages.size }

    Column(
        modifier = modifier.fillMaxSize()
            .background(CargoTheme.colorScheme.background)
            .padding(horizontal = CargoTheme.dimens.screenPaddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth().weight(0.7f),
            key = { index -> index }
        ) { pageIndex ->
            OnboardingPage(
                item = pages[pageIndex],
                currentPage = pagerState.currentPage,
                pageIndex = pageIndex,
                modifier = Modifier.fillMaxSize()
            )
        }

        if (pagerState.currentPage == pages.size - 1) {
            ColoredActionButton(
                text = stringResource(Res.string.get_started),
                onClick = onGetStartedClick,
                shape = CargoTheme.shapes.medium,
                textModifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.sm),
                modifier = Modifier.fillMaxWidth().weight(0.3f, fill = false)
                    .padding(horizontal = CargoTheme.dimens.screenPaddingHorizontal)
            )
        }
    }
}

data class OnBoardingPage(
    val imageRes: DrawableResource,
    val title: StringResource,
    val subtitle: StringResource
)

private fun getPages(): List<OnBoardingPage> {
    return listOf(
        OnBoardingPage(
            imageRes = Res.drawable.onboarding1,
            title = Res.string.onboardingOneTitle,
            subtitle = Res.string.onboardingOneSubtitle
        ),
        OnBoardingPage(
            imageRes = Res.drawable.onboarding2,
            title = Res.string.onboardingTwoTitle,
            subtitle =Res.string.onboardingTwoSubtitle
        ),
        OnBoardingPage(
            imageRes = Res.drawable.onboarding3,
            title = Res.string.onboardingThreeTitle,
            subtitle = Res.string.onboardingThreeSubtitle
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview(){
    OnboardingScreenContent(
        onGetStartedClick = {}
    )
}