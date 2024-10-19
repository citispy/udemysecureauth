package com.example.udemysecureauth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.navigation.compose.rememberNavController
import com.example.udemysecureauth.navigation.Navigation
import com.example.udemysecureauth.ui.theme.UdemySecureAuthTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UdemySecureAuthTheme(darkTheme = isSystemInDarkTheme()) {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}