package com.example.cargo_customer.presentation.screen.upload_docs.view.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.ColorFilter
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.upload_complete
import com.composables.icons.lucide.CircleCheck
import com.composables.icons.lucide.Lucide
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource


@Composable
fun SuccessOverlay() {

    val uploadCompleteText = stringResource(Res.string.upload_complete)

    Box(
        modifier = Modifier.fillMaxSize()
            .background(CargoTheme.extendedColors.onSuccess.copy(alpha = 0.6f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                Lucide.CircleCheck,
                contentDescription = uploadCompleteText,
                modifier = Modifier.size(CargoTheme.dimens.spacing.colossal),
                colorFilter = ColorFilter.tint(
                    CargoTheme.extendedColors.success
                )
            )

            Spacer(modifier = Modifier.height(CargoTheme.dimens.spacing.lg))

            Text(
                text = uploadCompleteText,
                color = CargoTheme.extendedColors.success,
                style = CargoTheme.typography.bodyLarge
            )
        }
    }
}

