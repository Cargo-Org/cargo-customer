package com.example.cargo_customer.presentation.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cargo_customer.composeapp.generated.resources.Res
import com.example.cargo_customer.presentation.theme.AppTextStyles
import com.example.cargo_customer.presentation.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    /*
    *column
    * - Box
    * --Column
    * ---Text
    * ---Text
    * ---CustomField
    * ---CustomField
    * ---TextButton
    * ---CustomButton
    * ---Row
    * ----Divider
    * ----Text
    * ----Divider
    * ---CustomButton
    * ---Row
    * ----Text
    * ----TextButton
    * */
    Box(
        modifier = modifier.fillMaxSize()
            .background(AppTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier=modifier.padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ){
            WelcomeHeader(modifier)
            InputField(
                modifier = modifier,
                value = "",
                onValueChanged = {},
                label = "Email or Phone Number",
                placeholder = "name@gmail.com",
            )
            InputField(
                modifier = modifier,
                value = "",
                onValueChanged = {},
                label = "Email or Phone Number",
                placeholder = "name@gmail.com",
            )
            ColoredActionButton(
                modifier = modifier ,
                onClick = {}
            )

        }

    }
}
@Composable
fun WelcomeHeader(modifier: Modifier){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier= Modifier.fillMaxWidth()
    ){
        Text(
            text="Welcome Back",
            style = AppTheme.typography.headlineXl,
            color = AppTheme.colors.primary,
            textAlign = TextAlign.Center)
        Text(text="Sign in to manage your shipments and track deliveries in real-time",
            style = AppTheme.typography.bodyStandard,
            color = AppTheme.colors.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(horizontal = 32.dp),)
    }
}

@Composable
fun InputField(
    modifier: Modifier,
    value:String,
    onValueChanged :(String)->Unit,
    label:String,
    placeholder:String,
    leadingIconRes: DrawableResource? = null,){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = label,
            style = AppTheme.typography.labelMd,
            color = AppTheme.colors.onSurfaceVariant)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    style = AppTheme.typography.bodyMd,
                    color = AppTheme.colors.onSurfaceVariant
                )
            },
            leadingIcon = leadingIconRes?.let { icon ->
                {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = null,
                        tint = AppTheme.colors.onSurfaceVariant,
                        modifier = Modifier.size(24.dp)
                    )
                }
            },
            shape = AppTheme.shapes.large,
            textStyle = AppTheme.typography.bodyMd,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppTheme.colors.surface,
                unfocusedContainerColor = AppTheme.colors.surface,
                focusedBorderColor = AppTheme.colors.outlineVariant,
                unfocusedBorderColor = AppTheme.colors.outlineVariant,
                focusedTextColor = AppTheme.colors.onSurface,
                unfocusedTextColor = AppTheme.colors.onSurface,
                focusedPlaceholderColor = AppTheme.colors.onSurfaceVariant,
                unfocusedPlaceholderColor = AppTheme.colors.onSurfaceVariant,
                cursorColor = AppTheme.colors.primary
            )
        )
    }
}

@Composable
fun ColoredActionButton(modifier: Modifier , onClick:()->Unit){
    Button(
        modifier=modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(size = 16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.primary,
            contentColor = AppTheme.colors.surface
        ),
    ){
        Text(
            modifier = modifier.padding(12.dp),
            text = "Create An Account",
            style = AppTheme.typography.bodyMd)
    }
}
@Composable
fun OrDivider(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Red)
        Text(" OR SIGN UP WITH ")
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Red)
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}