package com.example.cargo_customer.presentation.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_email
import cargo_customer.composeapp.generated.resources.ic_lock
import com.example.cargo_customer.presentation.auth.components.AuthFooterText
import com.example.cargo_customer.presentation.auth.components.ColoredActionButton
import com.example.cargo_customer.presentation.auth.components.GoogleButton
import com.example.cargo_customer.presentation.auth.components.InputField
import com.example.cargo_customer.presentation.auth.components.OrDivider
import com.example.cargo_customer.presentation.auth.components.WelcomeHeader
import com.example.cargo_customer.presentation.theme.AppTextStyles
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier=modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.End
        ){
            WelcomeHeader(
                modifier = modifier.padding(bottom = 28.dp),
                headerTitle = "Welcome Back",
                subTitle = "Sign in to manage your shipments and track deliveries in real-time",
            )
            InputField(
                modifier = modifier,
                value = "",
                onValueChanged = {},
                label = "Email or Phone Number",
                placeholder = "name@gmail.com",
                leadingIconRes = Res.drawable.ic_email
            )
            InputField(
                modifier = modifier,
                value = "",
                onValueChanged = {},
                label = "Password",
                placeholder = "* * * * *",
                leadingIconRes = Res.drawable.ic_lock
            )
            TextButton(
                onClick = {},
            ) {
                Text(
                    text = "Forget Password ?",
                    style = AppTheme.typography.labelMd,
                    color = AppTheme.colors.primary
                )
            }
            ColoredActionButton(
                modifier = modifier ,
                onClick = {}
            )
            OrDivider(
                modifier = modifier.padding(vertical = 16.dp),
                centerText = " OR SIGN IN "
            )
            GoogleButton(
                modifier = modifier ,
                onClick = {}
            )
            AuthFooterText(
                promptText = "Already have an account?",
                actionText = "Sign In",
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