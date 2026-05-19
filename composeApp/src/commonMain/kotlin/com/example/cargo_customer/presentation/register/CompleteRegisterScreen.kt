package com.example.cargo_customer.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import cargo_customer.composeapp.generated.resources.ic_email
import cargo_customer.composeapp.generated.resources.ic_phone
import cargo_customer.composeapp.generated.resources.ic_user
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.InputField
import com.example.cargo_customer.presentation.component.WelcomeHeader
import com.example.cargo_customer.presentation.theme.AppTheme

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
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .padding(horizontal = AppTheme.dimens.pageMargin),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.xs),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier=modifier.height(120.dp))
        WelcomeHeader(
            modifier = Modifier.fillMaxWidth(),
            headerTitle = "Complete your profile",
            subTitle = "Fill in your details to start ordering with Logistics Pro."
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.xs))
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChanged = {name = it},
            label = "Full Name",
            placeholder = "Ahmed Mohamed",
            leadingIconRes = Res.drawable.ic_user
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = phone,
            onValueChanged = {phone = it},
            label = "Phone Number",
            placeholder = "+(20) 111 111 1111",
            leadingIconRes = Res.drawable.ic_phone
        )
        InputField(
            modifier = Modifier.fillMaxWidth(),
            value = email,
            onValueChanged = {email = it},
            label = "Email Address",
            placeholder = "googleEmail@google.com",
            leadingIconRes = Res.drawable.ic_email
        )
        Spacer(modifier = Modifier.height(AppTheme.dimens.md))
        ColoredActionButton(
            modifier = modifier,
            onClick = {},
            text = "Send OTP"
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CompleteRegisterProfileScreenPreview() {
    CompleteRegisterProfileScreen()
}