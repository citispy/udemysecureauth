package com.lib.googlelogin

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    primaryText: String = "Sign in with Google",
    secondaryText: String = "Please wait...",
    onClicked: () -> Unit
) {
    val buttonText by remember(isLoading) {
        mutableStateOf(
            if (isLoading) secondaryText else primaryText
        )
    }

    Button(
        modifier = modifier,
        onClick = { onClicked() },
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
        modifier = Modifier.fillMaxWidth(),
        onClicked = {}
    )
}

@Preview
@Composable
fun GoogleButtonLoadingPreview() {
    GoogleButton(
        modifier = Modifier.fillMaxWidth(),
        isLoading = true,
        onClicked = {}
    )
}