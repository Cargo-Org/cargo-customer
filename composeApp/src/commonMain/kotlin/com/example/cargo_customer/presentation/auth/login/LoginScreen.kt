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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cargo_customer.presentation.theme.AppTextStyles
import com.example.cargo_customer.presentation.theme.AppTheme

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
        Column {
            WelcomeHeader(modifier)
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
    placeholder:String){
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Text(
            text = label,
            style = AppTheme.typography.labelMd,
            color = AppTheme.colors.onSurfaceVariant)
        TextField(
            value = value,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
        )
    }
}
@Composable
fun ColoredActionButton(modifier: Modifier){
    Button(modifier=modifier.fillMaxWidth(),onClick = {}){
        Text("s")
    }
}
@Composable
fun OrDivider(){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Red)
        Text(" OR ")
        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.Red)
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}