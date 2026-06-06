package com.example.cargo_customer.presentation.screen.upload_docs.viewmodel

data class UploadDocumentState(
    @Suppress("ArrayInDataClass")
    val frontImageBytes: ByteArray? = null,
    @Suppress("ArrayInDataClass")
    val backImageBytes: ByteArray? = null,
    @Suppress("ArrayInDataClass")
    val faceImageBytes : ByteArray?=null,
    val isLoading: Boolean = false,
    val step: UploadStep = UploadStep.IDLE,
    val stepIndex : Int = 0,
    val nationalImageIndex : Int = 0,
    val selectedDocumentType: DocumentType = DocumentType.NationalIdFront,
    val uploadUrl: String? = null,
    val objectKey: String? = null,
    val fileName: String = "",
    val contentType: String = "",
    val fileSizeBytes: Long = 0L,
    val error: String? = null
)

enum class UploadStep {
    IDLE,
    PICKED,
    GETTING_URL,
    UPLOADING,
    SUBMITTING,
    DONE,
    ERROR
}

enum class DocumentType (val value : Int)
{
    NationalIdFront(0),
    NationalIdBack(1),
    LiveFacePicture(3),
    Other (4)
}