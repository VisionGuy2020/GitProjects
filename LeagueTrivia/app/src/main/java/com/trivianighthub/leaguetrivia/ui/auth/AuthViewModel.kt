package com.trivianighthub.leaguetrivia.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.trivianighthub.leaguetrivia.util.DataResult
import com.trivianighthub.leaguetrivia.util.ErrorHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    private val _authState = MutableStateFlow<DataResult<Unit>?>(null)
    val authState: StateFlow<DataResult<Unit>?> = _authState

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = DataResult.Loading
                auth.signInWithEmailAndPassword(email, password).await()
                _authState.value = DataResult.Success(Unit)
            } catch (e: Exception) {
                _authState.value = DataResult.Error(ErrorHandler.handleException(e))
            }
        }
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                _authState.value = DataResult.Loading
                auth.createUserWithEmailAndPassword(email, password).await()
                _authState.value = DataResult.Success(Unit)
            } catch (e: Exception) {
                _authState.value = DataResult.Error(ErrorHandler.handleException(e))
            }
        }
    }

    fun signOut() {
        auth.signOut()
        _authState.value = null
    }

    fun clearAuthState() {
        _authState.value = null
    }
} 