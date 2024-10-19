package com.example.udemysecureauth.presentation.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lib.googlelogin.GoogleButton
import com.example.udemysecureauth.ui.theme.UdemySecureAuthTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {

    var isLoading by remember { mutableStateOf(false) }

    val signedInState by loginViewModel.signedInState

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        GoogleButton(
            modifier = Modifier
                .padding(32.dp)
                .align(Alignment.Center)
                .fillMaxWidth(),
            isLoading = isLoading
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    UdemySecureAuthTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            LoginScreen(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}