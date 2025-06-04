// Android Client Prototype - ui/screens/LoginScreen.kt
package com.example.diarydepresiku.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.diarydepresiku.ui.viewmodel.AuthViewModel
import com.example.diarydepresiku.util.Resource
import kotlinx.coroutines.flow.collectLatest

/**
 * Composable function for the Login Screen.
 *
 * @param viewModel Injected AuthViewModel instance.
 * @param onLoginSuccess Callback invoked when login is successful (e.g., to navigate to dashboard).
 * @param onNavigateToRegister Callback invoked to navigate to the registration screen.
 */
@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit
) {
    // State variables for email and password input fields
    var email by remember { mutableStateOf(\"\") }
    var password by remember { mutableStateOf(\"\") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Observe the login state from the ViewModel
    val loginState by viewModel.loginState.collectAsState()

    // Effect to handle login state changes (Loading, Success, Error)
    LaunchedEffect(loginState) {
        when (val state = loginState) {
            is Resource.Loading -> {
                isLoading = true
                errorMessage = null
            }
            is Resource.Success -> {
                isLoading = false
                errorMessage = null
                // Login successful, trigger navigation callback
                onLoginSuccess()
                viewModel.resetLoginState() // Reset state after consuming
            }
            is Resource.Error -> {
                isLoading = false
                errorMessage = state.message ?: \"An unknown error occurred\"
                // Keep the state until user tries again or navigates away
                // Optionally reset state after showing message: viewModel.resetLoginState()
            }
            null -> {
                // Initial or reset state
                isLoading = false
                errorMessage = null
            }
        }
    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(\"Login\", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(32.dp))

        // Email Input Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(\"Email\") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Input Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(\"Password\") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            enabled = !isLoading
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Display Error Message if any
        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Login Button
        Button(
            onClick = {
                // Basic validation
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.login(email, password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), strokeWidth = 2.dp)
            } else {
                Text(\"Login\")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Button to navigate to Register screen
        TextButton(onClick = onNavigateToRegister, enabled = !isLoading) {
            Text(\"Don\\'t have an account? Register\")
        }
    }
}

