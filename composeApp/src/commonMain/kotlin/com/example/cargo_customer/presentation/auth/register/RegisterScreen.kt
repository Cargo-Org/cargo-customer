package com.example.cargo_customer.presentation.auth.register

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.already_have_account
import cargo_customer.composeapp.generated.resources.create_an_account
import cargo_customer.composeapp.generated.resources.email_label
import cargo_customer.composeapp.generated.resources.email_phone_label
import cargo_customer.composeapp.generated.resources.email_placeholder
import cargo_customer.composeapp.generated.resources.forget_password
import cargo_customer.composeapp.generated.resources.ic_email
import cargo_customer.composeapp.generated.resources.ic_lock
import cargo_customer.composeapp.generated.resources.name_label
import cargo_customer.composeapp.generated.resources.name_placeholder
import cargo_customer.composeapp.generated.resources.or_sign_in
import cargo_customer.composeapp.generated.resources.password_label
import cargo_customer.composeapp.generated.resources.password_placeholder
import cargo_customer.composeapp.generated.resources.phone_label
import cargo_customer.composeapp.generated.resources.phone_placeholder
import cargo_customer.composeapp.generated.resources.sign_in_action
import cargo_customer.composeapp.generated.resources.sign_in_subtitle
import cargo_customer.composeapp.generated.resources.sign_up_subtitle
import cargo_customer.composeapp.generated.resources.welcome_back
import com.example.cargo_customer.presentation.auth.components.AuthFooterText
import com.example.cargo_customer.presentation.auth.components.ColoredActionButton
import com.example.cargo_customer.presentation.auth.components.GoogleButton
import com.example.cargo_customer.presentation.auth.components.InputField
import com.example.cargo_customer.presentation.auth.components.OrDivider
import com.example.cargo_customer.presentation.auth.components.WelcomeHeader
import com.example.cargo_customer.presentation.theme.AppTheme
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
                headerTitle = stringResource(Res.string.create_an_account),
                subTitle = stringResource(Res.string.sign_up_subtitle),
            )
            InputField(
                modifier = modifier,
                value = name,
                onValueChanged = {name = it},
                label = stringResource(Res.string.name_label),
                placeholder = stringResource(Res.string.name_placeholder),
                leadingIconRes = Res.drawable.ic_email
            )
            InputField(
                modifier = modifier,
                value = email,
                onValueChanged = {email = it},
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_label),
                leadingIconRes = Res.drawable.ic_email
            )
            InputField(
                modifier = modifier,
                value = phone,
                onValueChanged = {phone = it},
                label = stringResource(Res.string.phone_label),
                placeholder = stringResource(Res.string.phone_placeholder),
                leadingIconRes = Res.drawable.ic_email
            )
            InputField(
                modifier = modifier,
                value = password,
                onValueChanged = {password = it},
                label = stringResource(Res.string.password_label),
                placeholder = stringResource(Res.string.password_placeholder),
                leadingIconRes = Res.drawable.ic_lock
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
                text = stringResource(Res.string.create_an_account),
                onClick = {}
            )
            OrDivider(
                modifier = modifier.padding(vertical = AppTheme.dimens.gutter),
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
fun LoginScreenPreview() {
    RegisterScreen()
}
