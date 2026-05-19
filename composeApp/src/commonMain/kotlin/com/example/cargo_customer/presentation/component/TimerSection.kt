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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.ic_timer
import com.example.cargo_customer.presentation.theme.AppTheme
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
            tint = AppTheme.colors.outline,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "${minutes.toString().padStart(2, '0')}:${
                remainingSeconds.toString().padStart(2, '0')
            }",
            color = AppTheme.colors.outline,
            fontSize = 14.sp
        )
    }
}