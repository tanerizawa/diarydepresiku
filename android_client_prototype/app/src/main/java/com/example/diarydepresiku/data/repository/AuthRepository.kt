// Android Client Prototype - data/repository/AuthRepository.kt
package com.example.diarydepresiku.data.repository

import com.example.diarydepresiku.data.local.UserPreferences
import com.example.diarydepresiku.data.network.ApiService
import com.example.diarydepresiku.data.network.AuthRequest
import com.example.diarydepresiku.data.network.AuthResponse
import com.example.diarydepresiku.data.network.SimpleResponse
import com.example.diarydepresiku.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for handling authentication-related data operations.
 * It abstracts the data sources (network API, local preferences) from the ViewModel.
 */
@Singleton
class AuthRepository @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences
) {

    /**
     * Attempts to register a new user via the API.
     *
     * @param email User\'s email.
     * @param password User\'s password.
     * @param displayName Optional display name.
     * @return Resource<SimpleResponse> indicating success or failure.
     */
    suspend fun register(email: String, password: String, displayName: String?): Resource<SimpleResponse> {
        val request = AuthRequest(email = email, password = password, display_name = displayName)
        return try {
            val response = apiService.registerUser(request)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                // Handle specific HTTP error codes if needed
                Resource.Error(response.message() ?: "Registration failed")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn\'t reach server. Check your internet connection.")
        }
    }

    /**
     * Attempts to log in a user via the API.
     * If successful, saves the auth token and user info locally.
     *
     * @param email User\'s email.
     * @param password User\'s password.
     * @return Resource<AuthResponse> indicating success or failure.
     */
    suspend fun login(email: String, password: String): Resource<AuthResponse> {
        val request = AuthRequest(email = email, password = password)
        return try {
            val response = apiService.loginUser(request)
            if (response.isSuccessful && response.body() != null) {
                val authData = response.body()!!
                // Save token and user info on successful login
                userPreferences.saveAuthData(
                    token = authData.token,
                    email = authData.user.email,
                    displayName = authData.user.display_name
                )
                Resource.Success(authData)
            } else {
                Resource.Error(response.message() ?: "Invalid credentials or server error")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn\'t reach server. Check your internet connection.")
        }
    }

    /**
     * Logs out the user by clearing local authentication data.
     */
    suspend fun logout() {
        userPreferences.clearAuthData()
        // Optionally: Call a backend logout endpoint if it exists
    }

    /**
     * Provides a Flow to observe the current authentication token.
     *
     * @return Flow<String?> emitting the token or null.
     */
    fun getAuthTokenFlow(): Flow<String?> {
        return userPreferences.authToken
    }

    /**
    * Gets the current auth token directly (once).
    * Useful for making single API calls needing the token.
    */
    suspend fun getCurrentAuthToken(): String? {
        return userPreferences.authToken.firstOrNull()
    }

     /**
     * Provides a Flow to observe the current user\'s email.
     */
    fun getUserEmailFlow(): Flow<String?> {
        return userPreferences.userEmail
    }

    /**
     * Provides a Flow to observe the current user\'s display name.
     */
    fun getUserDisplayNameFlow(): Flow<String?> {
        return userPreferences.userDisplayName
    }
}

