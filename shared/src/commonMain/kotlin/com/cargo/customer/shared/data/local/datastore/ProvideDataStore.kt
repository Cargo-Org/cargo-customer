package com.cargo.customer.shared.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

internal const val DATA_STORE_FILE_NAME = "carog_customer.preferences_pb"

expect fun provideDataStore(): DataStore<Preferences>