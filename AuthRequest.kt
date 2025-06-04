// Android Client Prototype - data/network/AuthRequest.kt
package com.example.diarydepresiku.data.network

/**
 * Data class representing the request body for login and registration.
 * Note: Use @SerializedName if JSON keys differ from variable names.
 */
data class AuthRequest(
    val email: String,
    val password: String,
    val display_name: String? = null // Optional for registration
)

