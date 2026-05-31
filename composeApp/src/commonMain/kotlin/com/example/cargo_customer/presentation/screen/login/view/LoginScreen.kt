package com.example.cargo_customer.presentation.screen.login.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cargo_customer.composeapp.generated.resources.*
import com.example.cargo_customer.presentation.base.BaseViewModel
import com.example.cargo_customer.presentation.component.AuthFooterText
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.GoogleButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.OrDivider
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.navigation.LocalNavController
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginEffect
import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginInteraction
import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginUiState
import com.example.cargo_customer.presentation.screen.login.viewmodel.LoginViewModel
import com.example.cargo_customer.presentation.theme.CargoTheme
import kotlinx.coroutines.flow.collect
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel){
        viewModel.effect.collect{effect ->
            when(effect){
                is LoginEffect.NavigateToHome -> navController.navigate(Route.HomeRoute)
                is LoginEffect.NavigateToRegister -> navController.navigate(Route.RegisterRoute)
                is LoginEffect.NavigateToForgetPassword -> { }
                // one for verify
            }
        }
    }
    LoginScreenContent(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier,
    state: LoginUiState,
    onAction: (LoginInteraction)-> Unit,
) {
    val focusManager = LocalFocusManager.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CargoTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(horizontal = CargoTheme.dimens.screenPaddingHorizontal)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(CargoTheme.dimens.spacing.xs),
            horizontalAlignment = Alignment.End
        ) {
            WelcomeHeader(
                modifier = Modifier.padding(bottom = CargoTheme.dimens.spacing.xxl),
                headerTitle = stringResource(Res.string.welcome_back),
                subTitle = stringResource(Res.string.sign_in_subtitle),
            )
            InputField(
                value = state.email,
                onValueChanged = { onAction(LoginInteraction.OnEmailChanged(it)) },
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email,
                focusRequester = emailFocusRequester,
                errorMessage = state.errorMessage,
                onNext = { passwordFocusRequester.requestFocus() }
            )
            InputField(
                value = state.password,
                onValueChanged = { onAction(LoginInteraction.OnPasswordChanged(it)) },
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                leadingIconRes = Res.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                isPasswordVisible = state.isPasswordVisible,
                onVisibilityChange = { onAction(LoginInteraction.OnPasswordVisibilityToggled) },
                focusRequester = passwordFocusRequester,
                errorMessage = state.errorMessage,
                onDone = { focusManager.clearFocus() }
            )
            TextButton(onClick = { focusManager.clearFocus() }) {
                Text(
                    text = stringResource(Res.string.forget_password),
                    style = CargoTheme.typography.labelMedium,
                    color = CargoTheme.colorScheme.primary
                )
            }
            ColoredActionButton(
                text = stringResource(Res.string.sign_in_action),
                isLoading = state.isLoading,
                onClick = {
                    onAction(LoginInteraction.OnLoginClicked)
                    focusManager.clearFocus()
                }
            )
            OrDivider(
                modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.lg),
                centerText = stringResource(Res.string.or_sign_up)
            )
            GoogleButton(
                onClick = { onAction(LoginInteraction.OnGoogleClicked) }
            )
            AuthFooterText(
                promptText = stringResource(Res.string.already_have_account),
                actionText = stringResource(Res.string.create_an_account),
                onClick = { onAction(LoginInteraction.OnRegisterClicked) }
            )
        }
    }
}
