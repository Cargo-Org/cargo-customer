package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.OnClickCallback
import com.example.cargo_customer.presentation.theme.CargoTheme


@Composable
fun ClickableOverlay(
    text: String,
    icon: ImageVector? = null,
    contentColor: Color = Color.White,
    backgroundColor: Color = Color.Black,
    overlayOpacity : Float = 0.2f,
    onClick: OnClickCallback
) {
    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor.copy(alpha = overlayOpacity))
            .clickable { onClick() }, contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (icon != null) {
                Image(
                    icon,
                    contentDescription = text,
                    modifier = Modifier.size(CargoTheme.dimens.spacing.colossal),
                    colorFilter = ColorFilter.tint(contentColor)
                )
                Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.lg))
            }

            Text(
                text = text, color = contentColor, style = CargoTheme.typography.titleLarge
            )
        }
    }
}

