package com.example.cargo_customer.presentation.screen.verify_email

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_email
import cargo_customer.composeapp.generated.resources.verify_continue
import cargo_customer.composeapp.generated.resources.verify_identity
import cargo_customer.composeapp.generated.resources.verify_identity_description
import com.example.cargo_customer.presentation.component.ColoredActionButton
import com.example.cargo_customer.presentation.component.HeaderWithIcon
import com.example.cargo_customer.presentation.component.OTPVerificationSection
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun EmailVerificationScreen() {

    EmailVerificationContent(
        code = "12345",
        onCodeChange = {},
        secondsLeft = 23,
        onResendClick = {},
        onVerifyClick = {}
    )
}

@Composable
private fun EmailVerificationContent(
    code: String,
    onCodeChange: (String) -> Unit,
    secondsLeft: Int,
    onResendClick: () -> Unit,
    onVerifyClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CargoTheme.colorScheme.background)
            .padding(horizontal = CargoTheme.dimens.screenPaddingHorizontal),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.huge))

        HeaderWithIcon(
            painter = painterResource(Res.drawable.ic_email),
            title = stringResource(Res.string.verify_identity),
            description = stringResource(Res.string.verify_identity_description)
        )

        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.massive))

        OTPVerificationSection(
            codeLength = 5,
            code = code,
            onCodeChange = onCodeChange,
            secondsLeft = secondsLeft,
            onResendClick = onResendClick
        )

        Spacer(modifier = Modifier.weight(1f))
        ColoredActionButton(
            text = stringResource(Res.string.verify_continue),
            onClick = { if (code.length == 5) onVerifyClick() },
            enabled = code.length == 5
        )
        Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.massive))
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtPPreview() {
    EmailVerificationScreen()
}