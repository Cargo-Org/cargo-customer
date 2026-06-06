package com.example.cargo_customer.presentation.screen.upload_docs.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.identity_verification_subtitle
import cargo_customer.composeapp.generated.resources.identity_verification_tip_glasses
import cargo_customer.composeapp.generated.resources.identity_verification_tip_lighting
import cargo_customer.composeapp.generated.resources.identity_verification_title
import com.composables.icons.lucide.Glasses
import com.composables.icons.lucide.Lightbulb
import com.composables.icons.lucide.Lucide
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.FaceDetectionCard
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.TipRow
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun UploadScreenSecondStep(
    onFaceDetectionCardClick: () -> Unit, faceImage: ImageBitmap?
) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing
    val colorScheme = CargoTheme.colorScheme
    val typography = CargoTheme.typography

    Spacer(modifier = Modifier.height(spacing.xxl))

    Text(
        text = stringResource(Res.string.identity_verification_title),
        style = typography.headlineMedium,
        color = colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(spacing.sm))

    Text(
        text = stringResource(Res.string.identity_verification_subtitle),
        style = typography.bodyMedium,
        color = colorScheme.onSurfaceVariant
    )

    Spacer(modifier = Modifier.height(spacing.xxxl))

    FaceDetectionCard(
        image = faceImage,
        onClick = { onFaceDetectionCardClick() },
    )

    Spacer(modifier = Modifier.height(spacing.xxl))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(dimens.gridSpacing)
    ) {
        TipRow(
            icon = Lucide.Lightbulb,
            text = stringResource(Res.string.identity_verification_tip_lighting),
            modifier = Modifier.weight(1f)
        )
        TipRow(
            icon = Lucide.Glasses,
            text = stringResource(Res.string.identity_verification_tip_glasses),
            modifier = Modifier.weight(1f)
        )
    }
}