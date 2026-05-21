package com.example.cargo_customer.presentation.screen.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.cargo_customer.presentation.component.AuthFooterText
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.OrDivider
import com.example.cargo_customer.presentation.component.WelcomeHeader
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
            //TODO:Remove this to be match with login
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xs),
        ) {
            WelcomeHeader(
                //TODO: The padding her is very large
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
                leadingIconRes = Res.drawable.ic_user
            )
            InputField(
                modifier = modifier,
                value = email,
                onValueChanged = {email = it},
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_label),
                leadingIconRes = Res.drawable.ic_email,
                keyboardType = KeyboardType.Email
            )
            InputField(
                modifier = modifier,
                value = phone,
                onValueChanged = {phone = it},
                label = stringResource(Res.string.phone_label),
                placeholder = stringResource(Res.string.phone_placeholder),
                leadingIconRes = Res.drawable.ic_phone,
                keyboardType = KeyboardType.Phone
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
            //TODO:DON't SET Height WITH HARDCODED!!
            Spacer(modifier.height(30.dp))
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
fun RegisterPreview() {
    RegisterScreen()
}
