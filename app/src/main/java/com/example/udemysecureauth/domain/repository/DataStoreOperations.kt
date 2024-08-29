package com.example.udemysecureauth.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveSignInState(signedIn: Boolean)
    fun readSignInState(): Flow<Boolean>
}