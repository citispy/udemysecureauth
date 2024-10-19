package com.lib.googlelogin

import android.app.Activity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.libraries.identity.googleid.GetGoogleIdOption

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel(),
    isLoading: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait..."
) {
    val buttonText by remember(isLoading) {
        mutableStateOf(
            if (isLoading) secondaryText else primaryText
        )
    }

    val activityContext = LocalContext.current as Activity

    var attemptSignIn by remember { mutableStateOf(false) }
    LaunchedEffect(attemptSignIn) {
        if (attemptSignIn) {
            val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("478706453786-grqf2f6iarqtgfsikt6k022fm5psrbkq.apps.googleusercontent.com")
                .setAutoSelectEnabled(false)
                .build()

            val request: GetCredentialRequest = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()
            val credentialManager = CredentialManager.create(activityContext)
            try {
                val result = credentialManager.getCredential(
                    request = request,
                    context = activityContext,
                )
                viewModel.handleSignIn(result)
            } catch (e: GetCredentialException) {
                viewModel.handleFailure(e)
                attemptSignIn = false
            }
        }
    }

    Button(
        modifier = modifier,
        onClick = {
            attemptSignIn = true
        },
        enabled = !isLoading
    ) {
        Row(
            modifier = Modifier.animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = buttonText
            )
            if (isLoading) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator()
            }
        }
    }
}

@Preview
@Composable
fun GoogleButtonPreview() {
    GoogleButton(
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun GoogleButtonLoadingPreview() {
    GoogleButton(
        modifier = Modifier.fillMaxWidth(),
        isLoading = true
    )
}