package com.example.cargo_customer.presentation.mediapicker


import com.cargo.customer.shared.domain.model.MediaPickedFileModel
import platform.PhotosUI.PHPickerResult
import platform.PhotosUI.PHPickerViewController
import platform.PhotosUI.PHPickerViewControllerDelegateProtocol
import platform.darwin.NSObject

class PHPickerDelegate(
    private val onFilePicked: (MediaPickedFileModel?) -> Unit,
) : NSObject(), PHPickerViewControllerDelegateProtocol {

    override fun picker(
        picker: PHPickerViewController,
        didFinishPicking: List<*>,
    ) {
        picker.dismissViewControllerAnimated(true, completion = null)

        val result = didFinishPicking.firstOrNull() as? PHPickerResult ?: run {
            onFilePicked(null)
            return
        }

        result.itemProvider.loadDataRepresentationForTypeIdentifier("public.image") { data, _ ->
            onFilePicked(data?.toMediaPickedFile("picked"))
        }
    }
}