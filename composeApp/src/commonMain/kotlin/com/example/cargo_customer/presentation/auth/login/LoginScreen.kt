package com.example.cargo_customer.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import cargo_customer.composeapp.generated.resources.*
import com.example.cargo_customer.presentation.auth.components.AuthFooterText
import com.example.cargo_customer.presentation.auth.components.ColoredActionButton
import com.example.cargo_customer.presentation.auth.components.GoogleButton
import com.example.cargo_customer.presentation.auth.components.InputField
import com.example.cargo_customer.presentation.auth.components.OrDivider
import com.example.cargo_customer.presentation.auth.components.WelcomeHeader
import com.example.cargo_customer.presentation.theme.AppTheme
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = modifier.padding(horizontal = AppTheme.dimens.pageMargin),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xs),
            horizontalAlignment = Alignment.End
        ) {
            WelcomeHeader(
                modifier = modifier.padding(bottom = AppTheme.dimens.stackLg),
                headerTitle = stringResource(Res.string.welcome_back),
                subTitle = stringResource(Res.string.sign_in_subtitle),
            )
            InputField(
                modifier = modifier,
                value = email,
                onValueChanged = {email = it},
                label = stringResource(Res.string.email_phone_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email
            )
            InputField(
                modifier = modifier,
                value = password,
                onValueChanged = {password = it},
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                leadingIconRes = Res.drawable.ic_lock,
                keyboardType = KeyboardType.Password
            )
            TextButton(onClick = {}) {
                Text(
                    text = stringResource(Res.string.forget_password),
                    style = AppTheme.typography.labelMd,
                    color = AppTheme.colors.primary
                )
            }
            ColoredActionButton(
                modifier = modifier,
                text = stringResource(Res.string.sign_in_action),
                onClick = {}
            )
            OrDivider(
                modifier = modifier.padding(vertical = AppTheme.dimens.gutter),
                centerText = stringResource(Res.string.or_sign_up)
            )
            GoogleButton(
                modifier = modifier,
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