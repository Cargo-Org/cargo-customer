package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.resend_code
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun OTPVerificationSection(
    codeLength: Int = 5,
    code: String,
    onCodeChange: (String) -> Unit,
    secondsLeft: Int,
    onResendClick: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .background(
                AppTheme.colors.inverseOnSurface,
                shape = AppTheme.shapes.component4()
            )
            .wrapContentHeight()
            .padding(AppTheme.dimens.md),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(contentAlignment = Alignment.Center) {
            OTPInputRow(
                codeLength = codeLength,
                code = code,
                onBoxClick = { focusRequester.requestFocus() }
            )

            BasicTextField(
                value = code,
                onValueChange = { newValue ->
                    if (newValue.length <= codeLength && newValue.all { it.isDigit() }) {
                        onCodeChange(newValue)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                decorationBox = { innerTextField -> innerTextField() },
                cursorBrush = SolidColor(Color.Transparent),
                modifier = Modifier
                    .matchParentSize()
                    .focusRequester(focusRequester)
                    .alpha(0f)
            )
        }

        Spacer(modifier = Modifier.height(AppTheme.dimens.md))

        TimerSection(secondsLeft)

        Spacer(modifier = Modifier.height(AppTheme.dimens.base))

        Text(
            text = stringResource(Res.string.resend_code),
            color = AppTheme.colors.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onResendClick() }
        )
    }
}


@Preview
@Composable
fun OTPPreview() {
    var code by remember { mutableStateOf("12") }
    OTPVerificationSection(
        codeLength = 6,
        code = code,
        onCodeChange = { code = it },
        secondsLeft = 65,
        onResendClick = {}
    )
}
