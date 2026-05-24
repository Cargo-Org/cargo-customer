package com.example.cargo_customer.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.*
import com.example.cargo_customer.presentation.component.AuthFooterText
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.GoogleButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.OrDivider
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoginScreen() {
    LoginScreenContent()
}

@Composable
private fun LoginScreenContent(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
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
                value = email,
                onValueChanged = { email = it },
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email
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
                }
            )
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(Res.string.forget_password),
                    style = CargoTheme.typography.labelMedium,
                    color = CargoTheme.colorScheme.primary
                )
            }
            ColoredActionButton(
                text = stringResource(Res.string.sign_in_action),
                isLoading = true,
                onClick = {}
            )
            OrDivider(
                modifier = Modifier.padding(vertical = CargoTheme.dimens.spacing.lg),
                centerText = stringResource(Res.string.or_sign_up)
            )
            GoogleButton(
                onClick = {}
            )
            AuthFooterText(
                promptText = stringResource(Res.string.already_have_account),
                actionText = stringResource(Res.string.create_an_account),
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}