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
                fileName = fileName, contentType = contentType, fileSizeBytes = bytes.size.toLong()
            )
        }

        if (state.value.stepIndex == 0) {
            when (state.value.nationalImageIndex) {
                0 -> onFrontImageSelected(bytes)
                1 -> onBackImageSelected(bytes)
            }
        } else {
            onFaceDetectionImageSelected(bytes)
        }
    }

    private fun onFrontImageSelected(bytes: ByteArray) {
        updateState {
            copy(
                selectedDocumentType = DocumentType.NationalIdFront,
                step = UploadStep.PICKED,
                frontImageBytes = bytes
            )
        }
    }

    private fun onBackImageSelected(bytes: ByteArray) {
        updateState {
            copy(
                selectedDocumentType = DocumentType.NationalIdBack,
                step = UploadStep.PICKED,
                backImageBytes = bytes
            )
        }
    }

    private fun onFaceDetectionImageSelected(bytes: ByteArray) {
        updateState {
            copy(
                selectedDocumentType = DocumentType.LiveFacePicture,
                step = UploadStep.PICKED,
                faceImageBytes = bytes
            )
        }
    }

    override fun onPickDocumentClick() {
        sendEffect(UploadDocumentEffect.OpenImagePicker)
    }

    override fun onNationalIndexChangeBySwap(index: Int) {
        updateState {
            copy(
                nationalImageIndex = index
            )
        }
    }

    override fun onNextStepClick() {
        tryToExecute(
            onStart = { updateState { copy(step = UploadStep.GETTING_URL, isLoading = true) } },
            block = {
                getUploadUrlUseCase(
                    contentType = state.value.contentType,
                    documentType = state.value.selectedDocumentType.value
                )
            },
            onSuccess = { response ->
                when (response) {
                    is ApiResult.Success -> {
                        onUploadUrlSuccess(response.data.uploadUrl, response.data.objectKey)
                    }

                    is ApiResult.Error -> {
                        println("Error while Upload")
                    }
                }
            },
            onError = { updateState { copy(step = UploadStep.ERROR, error = "") } })
    }

    override fun onFaceDetectionClick() {
        sendEffect(UploadDocumentEffect.OpenImagePicker) //choose camera
    }

    private fun onUploadUrlSuccess(uploadUrl: String, objectKey: String) {
        updateState {
            copy(
                uploadUrl = uploadUrl,
                objectKey = objectKey,
                step = UploadStep.UPLOADING,
                stepIndex = 1
            )
        }
        uploadFile(uploadUrl, objectKey,getSelectedImage())
    }

    private fun uploadFile(uploadUrl: String, objectKey: String,selectedImage : ByteArray?) {
        tryToExecute(onStart = { updateState { copy(step = UploadStep.UPLOADING) } }, block = {
            uploadDocumentUseCase(
                url = uploadUrl,
                bytes = selectedImage!!, ///////////////////handle
            )
        }, onSuccess = { submitDocument(objectKey) }, onError = {
            updateState {
                copy(step = UploadStep.ERROR, error = "") ///////////
            }
        })
    }

    private fun getSelectedImage(): ByteArray? {
        return if (state.value.stepIndex == 0) {
            when (state.value.nationalImageIndex) {
                0 -> state.value.frontImageBytes
                else -> state.value.backImageBytes
            }
        } else {
            state.value.faceImageBytes
        }
    }

    private fun submitDocument(objectKey: String) {
        tryToExecute(onStart = { updateState { copy(step = UploadStep.SUBMITTING) } },
            block = {
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
                println("Done Submit Files")

        }, onError = {
            updateState { copy(step = UploadStep.ERROR, error = "") }
                println("Error while submit document data")

        })
    }

    override fun onFinishClick() {
        uploadFile(state.value.uploadUrl!!,state.value.objectKey!!,state.value.faceImageBytes)
    }
}