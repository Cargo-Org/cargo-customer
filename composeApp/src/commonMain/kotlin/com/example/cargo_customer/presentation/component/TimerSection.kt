package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_timer
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.painterResource


@Composable
fun TimerSection(secondsLeft: Int) {
    val minutes = secondsLeft / 60
    val remainingSeconds = secondsLeft % 60
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_timer),
            contentDescription = null,
            tint = CargoTheme.colorScheme.outline,
            modifier = Modifier.size(CargoTheme.dimens.sizing.iconXs)
        )

        Spacer(modifier = Modifier.width(CargoTheme.dimens.spacing.xxs))

        Text(
            text = "${minutes.toString().padStart(2, '0')}:${
                remainingSeconds.toString().padStart(2, '0')
            }",
            style = CargoTheme.typography.bodySmall,
            color = CargoTheme.colorScheme.outline
        )
    }
}