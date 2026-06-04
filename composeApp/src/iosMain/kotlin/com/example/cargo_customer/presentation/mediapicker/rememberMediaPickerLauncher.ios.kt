package com.example.cargo_customer.presentation.mediapicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.uikit.LocalUIViewController
import com.cargo.customer.shared.domain.model.MediaPickedFileModel

@Composable
actual fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFileModel?) -> Unit,
): MediaPickerLauncher {
    val uiViewController = LocalUIViewController.current
    val currentOnFilePicked by rememberUpdatedState(onFilePicked)

    return remember {
        MediaPickerLauncher(
            viewController = uiViewController,
            onFilePicked = currentOnFilePicked,
        )
    }
}


