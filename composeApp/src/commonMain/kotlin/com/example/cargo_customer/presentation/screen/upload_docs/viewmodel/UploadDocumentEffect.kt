package com.example.cargo_customer.presentation.screen.upload_docs.viewmodel

sealed interface UploadDocumentEffect {
    object OpenImagePicker : UploadDocumentEffect
    object NavigateToPendingScreen : UploadDocumentEffect
}