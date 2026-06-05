package com.example.cargo_customer.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.*
import com.example.cargo_customer.presentation.core.ui.asString
import com.example.cargo_customer.presentation.component.AuthFooterText
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.OrDivider
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.navigation.LocalNavController
import com.example.cargo_customer.presentation.navigation.Route
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RegisterScreen() {
    val viewModel: RegisterViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    LaunchedEffect(key1 = 1) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is RegisterUiEffect.NavigateToVerifyEmail -> {
                    navController.navigate(Route.VerifyEmail){
                        popUpTo(Route.RegisterRoute){
                            inclusive = true
                        }
                    }
                }
                is RegisterUiEffect.NavigateToLogin -> {
                    navController.navigate(Route.LoginRoute){
                        popUpTo(Route.RegisterRoute){
                            inclusive = true
                        }
                    }
                }
                is RegisterUiEffect.ShowToast -> {

                }
            }
        }
    }

    RegisterScreenContent(
        state = state,
        onInteraction = viewModel::onInteraction
    )
}

@Composable
private fun RegisterScreenContent(
    state: RegisterUiState,
    onInteraction: (RegisterInteraction) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val phoneFocusRequester = remember { FocusRequester() }
    val nameFocusRequester = remember { FocusRequester() }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(CargoTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = CargoTheme.dimens.screenPaddingHorizontal)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(CargoTheme.dimens.spacing.xs),
        ) {
            WelcomeHeader(
                modifier = Modifier.padding(bottom = CargoTheme.dimens.spacing.xxl),
                headerTitle = stringResource(Res.string.create_an_account),
                subTitle = stringResource(Res.string.sign_up_subtitle),
            )
            InputField(
                value = state.name,
                onValueChanged = {onInteraction(RegisterInteraction.OnNameChanged(it))},
                label = stringResource(Res.string.name_label),
                placeholder = stringResource(Res.string.name_placeholder),
                leadingIconRes = Res.drawable.ic_user,
                focusRequester = nameFocusRequester,
                onNext = { emailFocusRequester.requestFocus() },
                errorMessage = state.nameError?.asString()
            )
            InputField(
                value = state.email,
                onValueChanged = {onInteraction(RegisterInteraction.OnEmailChanged(it))},
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email,
                focusRequester = emailFocusRequester,
                onNext = { phoneFocusRequester.requestFocus() },
                errorMessage = state.emailError?.asString()
            )
            InputField(
                value = state.phone,
                onValueChanged = {onInteraction(RegisterInteraction.OnPhoneChanged(it))},
                label = stringResource(Res.string.phone_label),
                placeholder = stringResource(Res.string.phone_placeholder),
                leadingIconRes = Res.drawable.ic_phone,
                keyboardType = KeyboardType.Phone,
                focusRequester = phoneFocusRequester,
                onNext = { passwordFocusRequester.requestFocus() },
                errorMessage = state.phoneError?.asString()
            )
            InputField(
                value = state.password,
                onValueChanged = { onInteraction(RegisterInteraction.OnPasswordChanged(it)) },
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                leadingIconRes = Res.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                isPasswordVisible = state.isPasswordVisible,
                onVisibilityChange = { onInteraction(RegisterInteraction.OnTogglePasswordVisibility) },
                focusRequester = passwordFocusRequester,
                onDone = { focusManager.clearFocus() },
                errorMessage = state.passwordError?.asString()
            )

            if (state.error != null) {
                Text(
                    text = state.error.asString(),
                    color = Color.Red,
                    style = CargoTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = CargoTheme.dimens.spacing.xs)
                )
            }

            Spacer(Modifier.height(CargoTheme.dimens.spacing.xxxl))
            ColoredActionButton(
                text = stringResource(Res.string.create_an_account),
                isLoading = state.isLoading,
                onClick = {onInteraction(RegisterInteraction.OnRegisterClicked)}
            )
            OrDivider(
                modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.lg),
                centerText = stringResource(Res.string.or_sign_in)
            )
            AuthFooterText(
                promptText = stringResource(Res.string.already_have_account),
                actionText = stringResource(Res.string.sign_in_action),
                onClick = {onInteraction(RegisterInteraction.OnSignInClicked)}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    RegisterScreenContent(
        state = RegisterUiState(),
        onInteraction = {}
    )
}
