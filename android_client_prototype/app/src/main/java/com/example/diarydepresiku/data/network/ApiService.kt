// Android Client Prototype - data/network/ApiService.kt
package com.example.diarydepresiku.data.network

import retrofit2.Response // Import Response from Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Retrofit API interface defining the endpoints for the backend.
 */
interface ApiService {

    // --- Authentication Endpoints ---

    @POST("auth/register")
    suspend fun registerUser(
        @Body authRequest: AuthRequest
    ): Response<SimpleResponse> // Use Response<> for full HTTP response access

    @POST("auth/login")
    suspend fun loginUser(
        @Body authRequest: AuthRequest
    ): Response<AuthResponse>

    // --- Diary Entry Endpoints ---

    @POST("diary/entries")
    suspend fun createDiaryEntry(
        @Header("Authorization") token: String, // Pass JWT token in header
        @Body diaryEntryRequest: DiaryEntryRequest // Define DiaryEntryRequest data class
    ): Response<DiaryEntryResponse> // Define DiaryEntryResponse data class

    @GET("diary/entries")
    suspend fun getDiaryEntries(
        @Header("Authorization") token: String
    ): Response<DiaryEntriesListResponse> // Define DiaryEntriesListResponse data class

    // Add other endpoints here as needed (e.g., get specific entry, update, delete)
}

// --- Data classes for Diary Entry requests/responses (needs to be created) ---

// Request body for creating a diary entry
data class DiaryEntryRequest(
    val emotion_category: String?,
    val emotion_intensity: Int?,
    val notes: String?,
    val entry_timestamp: String? = null // Optional: ISO 8601 format string e.g., "2023-10-27T10:15:30Z"
)

// Response for a single diary entry (e.g., after creation)
data class DiaryEntryResponse(
    val message: String? = null, // Optional message
    val entry: DiaryEntryDto
)

// Response containing a list of diary entries
data class DiaryEntriesListResponse(
    val entries: List<DiaryEntryDto>
)

// Data Transfer Object (DTO) for a diary entry
data class DiaryEntryDto(
    val id: Int,
    val entry_timestamp: String, // ISO 8601 format string
    val emotion_category: String?,
    val emotion_intensity: Int?,
    val notes: String?
    // Add other fields matching the backend response
)

