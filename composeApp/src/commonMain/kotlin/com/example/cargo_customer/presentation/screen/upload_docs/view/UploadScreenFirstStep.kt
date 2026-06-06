package com.example.cargo_customer.presentation.screen.upload_docs.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import cargo_customer.composeapp.generated.resources.Res
import cargo_customer.composeapp.generated.resources.upload_docs_subtitle
import cargo_customer.composeapp.generated.resources.upload_docs_tip_corners
import cargo_customer.composeapp.generated.resources.upload_docs_tip_lighting
import cargo_customer.composeapp.generated.resources.upload_docs_tip_readable
import cargo_customer.composeapp.generated.resources.upload_docs_tips_heading
import cargo_customer.composeapp.generated.resources.upload_docs_title
import com.composables.icons.lucide.Crop
import com.composables.icons.lucide.FileCheckCorner
import com.composables.icons.lucide.Lightbulb
import com.composables.icons.lucide.Lucide
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.NationalIDCard
import com.example.cargo_customer.presentation.screen.upload_docs.view.component.TipRow
import com.example.cargo_customer.presentation.theme.CargoTheme
import org.jetbrains.compose.resources.stringResource

@Composable
fun UploadScreenFirstStep(
    nationalIDCardIndex: Int,
    frontImage: ImageBitmap?,
    backImage: ImageBitmap?,
    onNationalIDIndexChanged: (Int) -> Unit,
    onFrontClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val dimens = CargoTheme.dimens
    val spacing = dimens.spacing

    Spacer(Modifier.height(spacing.xxl))

    Text(
        text = stringResource(Res.string.upload_docs_title),
        style = CargoTheme.typography.headlineMedium,
        color = CargoTheme.extendedColors.textPrimary
    )

    Spacer(Modifier.height(spacing.sm))

    Text(
        text = stringResource(Res.string.upload_docs_subtitle),
        style = CargoTheme.typography.bodyMedium,
        color = CargoTheme.extendedColors.textSecondary
    )

    Spacer(Modifier.height(spacing.xxxl))

    NationalIDCard(
        currentIndex = nationalIDCardIndex,
        frontImage = frontImage,
        backImage = backImage,
        onFrontClick = onFrontClick,
        onBackClick = onBackClick,
        onIndexChanged = onNationalIDIndexChanged
    )

    Spacer(Modifier.height(spacing.xxl))

    Text(
        text = stringResource(Res.string.upload_docs_tips_heading),
        style = CargoTheme.typography.labelLarge,
        color = CargoTheme.colorScheme.onBackground.copy(alpha = 0.6f)
    )

    Spacer(Modifier.height(spacing.lg))

    TipRow(
        text = stringResource(Res.string.upload_docs_tip_lighting), icon = Lucide.Lightbulb
    )

    Spacer(Modifier.height(spacing.lg))

    TipRow(
        text = stringResource(Res.string.upload_docs_tip_corners), icon = Lucide.Crop
    )

    Spacer(Modifier.height(spacing.lg))

    TipRow(
        text = stringResource(Res.string.upload_docs_tip_readable), icon = Lucide.FileCheckCorner
    )
}