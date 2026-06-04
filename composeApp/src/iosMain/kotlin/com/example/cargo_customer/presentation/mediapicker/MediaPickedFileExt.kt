package com.example.cargo_customer.presentation.mediapicker


import com.cargo.customer.shared.domain.model.MediaPickedFileModel
import okio.ByteString.Companion.toByteString
import platform.Foundation.NSCalendar
import platform.Foundation.NSData
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.timeIntervalSinceDate

internal fun NSData.toMediaPickedFile(prefix: String): MediaPickedFileModel {
    val calendar = NSCalendar.currentCalendar
    val components = NSDateComponents().apply {
        year = 2003
        month = 4
        day = 27
    }

    val specificDate = calendar.dateFromComponents(components) ?: NSDate()

    val secondsSinceCustomDate = NSDate().timeIntervalSinceDate(specificDate).toLong()

    val bytes = toByteString().toByteArray()

    return MediaPickedFileModel(
        name = "${prefix}_${secondsSinceCustomDate}.jpg",
        sizeBytes = bytes.size.toLong(),
        mimeType = "image/jpeg",
        bytes = bytes,
    )
}