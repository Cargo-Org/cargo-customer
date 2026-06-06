package com.example.cargo_customer.presentation.screen.upload_docs.viewmodel

interface UploadDocumentInteractionListener {
    fun onPickDocumentClick()
    fun onNationalIndexChangeBySwap(index: Int)
    fun onNextStepClick()
    fun onFaceDetectionClick()
    fun onFinishClick()
}