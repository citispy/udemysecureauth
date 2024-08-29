package com.example.udemysecureauth.data.repository

import com.example.udemysecureauth.domain.repository.DataStoreOperations
import com.example.udemysecureauth.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
): Repository {
    override suspend fun saveSignedInState(signedIn: Boolean) {
        dataStoreOperations.saveSignInState(signedIn)
    }

    override fun readSignedInState(): Flow<Boolean> {
        return dataStoreOperations.readSignInState()
    }
}