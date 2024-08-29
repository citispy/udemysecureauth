package com.example.udemysecureauth.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.example.udemysecureauth.domain.repository.DataStoreOperations
import com.example.udemysecureauth.data.repository.DataStoreOperationsImpl.PreferencesKey.signedInKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreOperationsImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): DataStoreOperations {

    private object PreferencesKey {
        val signedInKey = booleanPreferencesKey(name = "signed_in_key")
    }
    override suspend fun saveSignInState(signedIn: Boolean) {
        dataStore.edit {
            it[signedInKey] = signedIn
        }
    }

    override fun readSignInState(): Flow<Boolean> {
        return dataStore.data.map {
            it[signedInKey] ?: false
        }
    }
}