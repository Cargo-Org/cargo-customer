package com.example.cargo_customer.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import cargo_customer.composeapp.generated.resources.complete_profile_subtitle
import cargo_customer.composeapp.generated.resources.complete_your_profile
import cargo_customer.composeapp.generated.resources.email_label
import cargo_customer.composeapp.generated.resources.email_placeholder
import cargo_customer.composeapp.generated.resources.ic_email
import cargo_customer.composeapp.generated.resources.ic_phone
import cargo_customer.composeapp.generated.resources.ic_user
import cargo_customer.composeapp.generated.resources.name_label
import cargo_customer.composeapp.generated.resources.name_placeholder
import cargo_customer.composeapp.generated.resources.phone_label
import cargo_customer.composeapp.generated.resources.phone_placeholder
import cargo_customer.composeapp.generated.resources.send_otp
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun CompleteRegisterProfileScreen() {
    CompleteRegisterProfileScreenContent()
}

@Composable
private fun CompleteRegisterProfileScreenContent(
    modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf("")}
    var phone by remember { mutableStateOf("")}
    var name by remember { mutableStateOf("") }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = modifier.padding(horizontal = AppTheme.dimens.pageMargin),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xs),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            WelcomeHeader(
                modifier = Modifier.fillMaxWidth(),
                headerTitle = stringResource(Res.string.complete_your_profile),
                subTitle = stringResource(Res.string.complete_profile_subtitle)
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.xs))
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChanged = {name = it},
                label = stringResource(Res.string.name_label),
                placeholder = stringResource(Res.string.name_placeholder),
                leadingIconRes = Res.drawable.ic_user
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = phone,
                onValueChanged = {phone = it},
                label = stringResource(Res.string.phone_label),
                placeholder = stringResource(Res.string.phone_placeholder),
                leadingIconRes = Res.drawable.ic_phone
            )
            InputField(
                modifier = Modifier.fillMaxWidth(),
                value = email,
                onValueChanged = {email = it},
                label = stringResource(Res.string.email_label),
                placeholder = stringResource(Res.string.email_placeholder),
                leadingIconRes = Res.drawable.ic_email
            )
            Spacer(modifier = Modifier.height(AppTheme.dimens.md))
            ColoredActionButton(
                modifier = modifier,
                onClick = {},
                text = stringResource(Res.string.send_otp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CompleteRegisterProfileScreenPreview() {
    CompleteRegisterProfileScreen()
}