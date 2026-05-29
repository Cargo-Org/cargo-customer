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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.*
import com.example.cargo_customer.presentation.component.AuthFooterText
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.OrDivider
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun RegisterScreen() {
    RegisterScreenContent()
}

@Composable
private fun RegisterScreenContent(
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val phoneFocusRequester = remember { FocusRequester() }
    val nameFocusRequester = remember { FocusRequester() }

    var isLoading by remember { mutableStateOf(false) }

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
        ) {
            WelcomeHeader(
                modifier = Modifier.padding(bottom = CargoTheme.dimens.spacing.xxl),
                headerTitle = stringResource(Res.string.create_an_account),
                subTitle = stringResource(Res.string.sign_up_subtitle),
            )
            InputField(
                value = name,
                onValueChanged = { name = it },
                label = stringResource(Res.string.name_label),
                placeholder = stringResource(Res.string.name_placeholder),
                leadingIconRes = Res.drawable.ic_user,
                focusRequester = nameFocusRequester,
                onNext = { emailFocusRequester.requestFocus() }
            )
            InputField(
                value = email,
                onValueChanged = { email = it },
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email,
                focusRequester = emailFocusRequester,
                onNext = { phoneFocusRequester.requestFocus() }
            )
            InputField(
                value = phone,
                onValueChanged = { phone = it },
                label = stringResource(Res.string.phone_label),
                placeholder = stringResource(Res.string.phone_placeholder),
                leadingIconRes = Res.drawable.ic_phone,
                keyboardType = KeyboardType.Phone,
                focusRequester = phoneFocusRequester,
                onNext = { passwordFocusRequester.requestFocus() }
            )
            InputField(
                value = password,
                onValueChanged = { password = it },
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                leadingIconRes = Res.drawable.ic_lock,
                keyboardType = KeyboardType.Password,
                isPasswordField = true,
                isPasswordVisible = passwordVisible,
                onVisibilityChange = {
                    passwordVisible = !passwordVisible
                },
                focusRequester = passwordFocusRequester,
                onDone = { focusManager.clearFocus() }
            )
            Spacer(Modifier.height(CargoTheme.dimens.spacing.xxxl))
            ColoredActionButton(
                text = stringResource(Res.string.create_an_account),
                isLoading = isLoading,
                onClick = {}
            )
            OrDivider(
                modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.lg),
                centerText = stringResource(Res.string.or_sign_in)
            )
            AuthFooterText(
                promptText = stringResource(Res.string.already_have_account),
                actionText = stringResource(Res.string.sign_in_action),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterPreview() {
    RegisterScreen()
}
