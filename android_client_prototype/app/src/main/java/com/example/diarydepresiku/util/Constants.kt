// Android Client Prototype - util/Constants.kt
package com.example.diarydepresiku.util

/**
 * Object to hold constant values used throughout the application.
 */
object Constants {
    // Base URL for the backend API.
    // Replace with your actual backend server address.
    // For local development using emulator, use 10.0.2.2 to refer to the host machine.
    const val BASE_URL = "http://10.0.2.2:5000/" // Adjust if backend runs on a different port or host

    // Preference keys for DataStore (example)
    const val PREF_AUTH_TOKEN = "auth_token"
    const val PREF_USER_EMAIL = "user_email"
    const val PREF_USER_DISPLAY_NAME = "user_display_name"

    // Other constants can be added here (e.g., database name, API endpoints if not in Retrofit interface)
}

