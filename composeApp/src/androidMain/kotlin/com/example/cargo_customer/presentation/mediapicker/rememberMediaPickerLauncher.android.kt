package com.example.cargo_customer.presentation.mediapicker

import android.Manifest
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.cargo.customer.shared.domain.model.MediaPickedFileModel
import java.io.File

@Composable
actual fun rememberMediaPickerLauncher(
    onFilePicked: (MediaPickedFileModel?) -> Unit,
): MediaPickerLauncher {

    val context = LocalContext.current
    var pendingCameraFile by remember { mutableStateOf<File?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        val file = pendingCameraFile
        if (success && file != null && file.exists()) {
            onFilePicked(
                MediaPickedFileModel(
                    name = file.name,
                    sizeBytes = file.length(),
                    mimeType = "image/jpeg",
                    bytes = file.readBytes(),
                )
            )
        } else {
            onFilePicked(null)
        }
        file?.delete()
        pendingCameraFile = null
    }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            val (file, uri) = CameraFileProvider.createTempImageUri(context)
            pendingCameraFile = file
            cameraLauncher.launch(uri)
        } else {
            onFilePicked(null)
        }
    }

    val fileLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        onFilePicked(uri?.toMediaPickedFile(context.contentResolver))
    }

    return remember(cameraLauncher, fileLauncher, permissionLauncher) {
        MediaPickerLauncher(
            onLaunchCamera = {
                val permission = Manifest.permission.CAMERA
                if (ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    val (file, uri) = CameraFileProvider.createTempImageUri(context)
                    pendingCameraFile = file
                    cameraLauncher.launch(uri)
                } else {
                    permissionLauncher.launch(permission)
                }
            },
            onLaunchFilePicker = { fileLauncher.launch("*/*") },
        )
    }
}

private fun Uri.toMediaPickedFile(resolver: ContentResolver): MediaPickedFileModel? = try {
    val mimeType = resolver.getType(this) ?: "application/octet-stream"


    var name = "file_${System.currentTimeMillis()}"
    var size = 0L

    resolver.query(this, null, null, null, null)?.use { cursor ->
        if (cursor.moveToFirst()) {
            val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            val sizeIndex = cursor.getColumnIndex(OpenableColumns.SIZE)
            if (nameIndex >= 0) name = cursor.getString(nameIndex)
            if (sizeIndex >= 0) size = cursor.getLong(sizeIndex)
        }
    }

    val bytes = resolver.openInputStream(this)?.use { it.readBytes() } ?: return null

    MediaPickedFileModel(
        name = name,
        sizeBytes = size.takeIf { it > 0 } ?: bytes.size.toLong(),
        mimeType = mimeType,
        bytes = bytes,
    )
} catch (e: Exception) {
    null
}