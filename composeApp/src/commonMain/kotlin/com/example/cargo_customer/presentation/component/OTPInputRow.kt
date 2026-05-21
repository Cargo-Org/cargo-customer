package com.example.cargo_customer.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cargo_customer.presentation.theme.AppTheme

@Composable
fun OTPInputRow(
    codeLength: Int,
    code: String,
    onBoxClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(codeLength) { index ->
            val char = code.getOrNull(index)?.toString() ?: ""
            val isActive = index == code.length
            val isFilled = index < code.length

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        color = if (isFilled) AppTheme.colors.surfaceContainerHigh else AppTheme.colors.surface,
                        shape = AppTheme.shapes.component4()
                    )
                    .border(
                        width = if (isActive) 2.dp else 1.dp,
                        color = when {
                            isActive -> AppTheme.colors.primary
                            isFilled -> AppTheme.colors.primary.copy(alpha = 0.4f)
                            else -> AppTheme.colors.outline.copy(alpha = 0.4f)
                        },
                        shape = AppTheme.shapes.component4()
                    )
                    .clickable { onBoxClick() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = char,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}