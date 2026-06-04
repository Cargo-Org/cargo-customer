package com.example.cargo_customer.presentation.screen.upload_docs.viewmodel

import com.cargo.customer.shared.domain.result.ApiResult
import com.cargo.customer.shared.domain.usecase.GetUploadUrlUseCase
import com.cargo.customer.shared.domain.usecase.SubmitDocumentInfoUseCase
import com.cargo.customer.shared.domain.usecase.UploadDocumentUseCase
import com.example.cargo_customer.presentation.base.BaseViewModel

class UploadDocumentViewModel(
    private val getUploadUrlUseCase: GetUploadUrlUseCase,
    private val uploadDocumentUseCase: UploadDocumentUseCase,
    private val submitDocumentInfoUseCase: SubmitDocumentInfoUseCase
) : BaseViewModel<UploadDocumentState, UploadDocumentEffect>(UploadDocumentState()),
    UploadDocumentInteractionListener {

    fun onImageSelected(bytes: ByteArray, fileName: String, contentType: String) {
        updateState {
            copy(
                selectedImage = bytes,
                fileName = fileName,
                contentType = contentType,
                fileSizeBytes = bytes.size.toLong()
            )
        }
    }

    override fun onPickDocumentClick() {
        sendEffect(UploadDocumentEffect.OpenImagePicker)
    }

    override fun onNextStepClick() {
        tryToExecute(
            onStart = { updateState { copy(step = UploadStep.GETTING_URL, isLoading = true) } },
            block = { getUploadUrlUseCase() },
            onSuccess = { response ->
                when (response) {
                    is ApiResult.Success -> {
                        onUploadUrlSuccess(response.data.uploadUrl, response.data.objectKey)
                    }

                    is ApiResult.Error -> {}
                }
            },
            onError = { updateState { copy(step = UploadStep.ERROR, error = "") } })
    }

    private fun onUploadUrlSuccess(uploadUrl: String, objectKey: String) {
        updateState {
            copy(
                uploadUrl = uploadUrl,
                objectKey = objectKey,
                step = UploadStep.UPLOADING,
            )
        }
        uploadFile(uploadUrl, objectKey)
    }

    private fun uploadFile(uploadUrl: String, objectKey: String) {
        tryToExecute(onStart = { updateState { copy(step = UploadStep.UPLOADING) } }, block = {
            uploadDocumentUseCase(
                url = uploadUrl,
                bytes = state.value.selectedImage!!, ///////////////////handle
            )
        }, onSuccess = { submitDocument(objectKey) }, onError = {
            updateState {
                copy(step = UploadStep.ERROR, error = "") ///////////
            }
        })
    }

    private fun submitDocument(objectKey: String) {
        tryToExecute(onStart = { updateState { copy(step = UploadStep.SUBMITTING) } }, block = {
            submitDocumentInfoUseCase(
                documentType = state.value.selectedDocumentType.value,
                fileName = state.value.fileName,
                key = objectKey,
                contentType = state.value.contentType,
                fileSizeInByte = state.value.fileSizeBytes.toString()
            )
        }, onSuccess = {
            updateState {
                copy(
                    step = UploadStep.DONE,
                    selectedDocumentType = DocumentType.NationalIdBack,
                    isLoading = false
                )
            }
            sendEffect(UploadDocumentEffect.NavigateToPendingScreen)
        }, onError = { updateState { copy(step = UploadStep.ERROR, error = "") } })
    }

    override fun onFinishClick() {
        ///TODO navigate to pending screen
    }
}