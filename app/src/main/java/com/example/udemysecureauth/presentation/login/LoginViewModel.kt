package com.example.udemysecureauth.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.udemysecureauth.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var signedInState = mutableStateOf(false)
        private set

    init {
        collectSignedInState()
    }

    private fun collectSignedInState() {
        viewModelScope.launch {
            repository.readSignedInState().collect {
                signedInState.value = it
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch {
            repository.saveSignedInState(signedIn)
        }
    }
}