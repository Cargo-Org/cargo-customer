package com.example.cargo_customer.presentation.mediapicker

import androidx.compose.runtime.Composable
import com.cargo.customer.shared.domain.model.MediaPickedFileModel

@Composable
expect fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFileModel?) -> Unit,
): MediaPickerLauncher