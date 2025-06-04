// Android Client Prototype - ui/viewmodel/AuthViewModel.kt
package com.example.diarydepresiku.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diarydepresiku.data.network.AuthResponse
import com.example.diarydepresiku.data.network.SimpleResponse
import com.example.diarydepresiku.data.repository.AuthRepository
import com.example.diarydepresiku.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for handling authentication logic (login, registration, logout)
 * and managing authentication-related UI state.
 */
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    // --- Login State ---
    private val _loginState = MutableStateFlow<Resource<AuthResponse>?>(null) // Start with null state
    val loginState: StateFlow<Resource<AuthResponse>?> = _loginState.asStateFlow()

    /**
     * Attempts to log in the user.
     * Updates the _loginState Flow with the result (Loading, Success, Error).
     */
    fun login(email: String, password: String) {
        viewModelScope.launch {
            _loginState.value = Resource.Loading() // Set state to Loading
            val result = authRepository.login(email, password)
            _loginState.value = result // Update state with Success or Error
        }
    }

    /**
     * Resets the login state, typically after the UI has consumed the result.
     */
    fun resetLoginState() {
        _loginState.value = null
    }

    // --- Registration State ---
    private val _registerState = MutableStateFlow<Resource<SimpleResponse>?>(null)
    val registerState: StateFlow<Resource<SimpleResponse>?> = _registerState.asStateFlow()

    /**
     * Attempts to register a new user.
     * Updates the _registerState Flow with the result.
     */
    fun register(email: String, password: String, displayName: String?) {
        viewModelScope.launch {
            _registerState.value = Resource.Loading()
            val result = authRepository.register(email, password, displayName)
            _registerState.value = result
        }
    }

    /**
     * Resets the registration state.
     */
    fun resetRegisterState() {
        _registerState.value = null
    }

    // --- Logout ---
    /**
     * Logs out the current user by clearing local data.
     */
    fun logout() {
        viewModelScope.launch {
            authRepository.logout()
            // Optionally: Navigate user back to login screen after logout
            // This navigation logic is usually handled in the UI observing a state change
        }
    }

    // --- Observing Auth State ---
    /**
     * Provides a Flow to observe the authentication token.
     * The UI can collect this flow to determine if a user is logged in.
     */
    fun observeAuthToken(): Flow<String?> {
        return authRepository.getAuthTokenFlow()
    }

     /**
     * Provides a Flow to observe the user\\'s email.
     */
    fun observeUserEmail(): Flow<String?> {
        return authRepository.getUserEmailFlow()
    }

     /**
     * Provides a Flow to observe the user\\'s display name.
     */
    fun observeUserDisplayName(): Flow<String?> {
        return authRepository.getUserDisplayNameFlow()
    }
}

