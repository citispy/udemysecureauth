package com.lib.googlelogin

import android.util.Log
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(): ViewModel() {
    fun handleSignIn(result: GetCredentialResponse) {
        Log.d("SignInViewModel", result.toString())
        when(val credential = result.credential) {
            is CustomCredential -> {
                if(credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    val token = GoogleIdTokenCredential.createFrom(credential.data)
                    val verifier = GoogleIdTokenVerifier.Builder(NetHttpTransport(), GsonFactory())
                        .setAudience(listOf(Constants.AUDIENCE))
                        .setIssuer(Constants.ISSUER)
                        .build()
                    // TODO: Add try catch for verification and inject dispatcher
                    viewModelScope.launch(Dispatchers.IO) {
                        val idToken = verifier.verify(token.idToken)
                        Log.d(SignInViewModel::class.java.name, "token: ${token.idToken}")
                        Log.d(SignInViewModel::class.java.name, "idToken: $idToken")
                    }
                }
            }
            else -> Unit
        }
    }

    fun handleFailure(e: GetCredentialException) {
        // TODO: Handle failures
        Log.d("SignInViewModel", e.toString())
    }
}