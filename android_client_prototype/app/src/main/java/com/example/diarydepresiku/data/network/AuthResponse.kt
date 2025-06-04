// Android Client Prototype - data/network/AuthResponse.kt
package com.example.diarydepresiku.data.network

/**
 * Data class representing the response body for a successful login.
 * Note: Use @SerializedName if JSON keys differ from variable names.
 */
data class AuthResponse(
    val message: String,
    val token: String,
    val user: UserInfo
)

/**
 * Data class holding basic user information returned upon login.
 */
data class UserInfo(
    val id: Int,
    val email: String,
    val display_name: String?
)

/**
 * Generic response for actions like registration (where only a message is needed).
 */
data class SimpleResponse(
    val message: String
)

