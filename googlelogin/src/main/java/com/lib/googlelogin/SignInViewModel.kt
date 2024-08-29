package com.lib.googlelogin

import android.credentials.GetCredentialException
import androidx.credentials.GetCredentialRequest
import androidx.lifecycle.ViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

class SignInViewModel: ViewModel() {
    fun signIn() {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId("478706453786-grqf2f6iarqtgfsikt6k022fm5psrbkq.apps.googleusercontent.com")
            .setAutoSelectEnabled(true)
        .build()

        val request: GetCredentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }
}