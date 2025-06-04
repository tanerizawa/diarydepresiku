// Android Client Prototype - data/repository/DiaryRepository.kt
package com.example.diarydepresiku.data.repository

import com.example.diarydepresiku.data.local.UserPreferences
import com.example.diarydepresiku.data.network.ApiService
import com.example.diarydepresiku.data.network.DiaryEntriesListResponse
import com.example.diarydepresiku.data.network.DiaryEntryRequest
import com.example.diarydepresiku.data.network.DiaryEntryResponse
import com.example.diarydepresiku.util.Resource
import kotlinx.coroutines.flow.firstOrNull
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for handling diary entry related data operations.
 * Abstracts data sources (network API) from the ViewModel.
 */
@Singleton
class DiaryRepository @Inject constructor(
    private val apiService: ApiService,
    private val userPreferences: UserPreferences // Needed to get the auth token
) {

    /**
     * Creates a new diary entry via the API.
     *
     * @param emotionCategory Category of the emotion.
     * @param emotionIntensity Intensity of the emotion (e.g., 1-5).
     * @param notes Free text notes for the entry.
     * @return Resource<DiaryEntryResponse> indicating success or failure.
     */
    suspend fun createDiaryEntry(
        emotionCategory: String?,
        emotionIntensity: Int?,
        notes: String?
    ): Resource<DiaryEntryResponse> {
        // Get the auth token first
        val token = userPreferences.authToken.firstOrNull()
        if (token == null) {
            return Resource.Error("User not authenticated")
        }

        val request = DiaryEntryRequest(
            emotion_category = emotionCategory,
            emotion_intensity = emotionIntensity,
            notes = notes
            // Timestamp is handled by backend by default, or can be added here if needed
        )

        return try {
            // Add "Bearer " prefix to the token for the header
            val response = apiService.createDiaryEntry(token = "Bearer $token", diaryEntryRequest = request)
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Failed to create entry")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn\\'t reach server. Check your internet connection.")
        }
    }

    /**
     * Retrieves all diary entries for the authenticated user from the API.
     *
     * @return Resource<DiaryEntriesListResponse> containing the list of entries or an error.
     */
    suspend fun getDiaryEntries(): Resource<DiaryEntriesListResponse> {
        val token = userPreferences.authToken.firstOrNull()
        if (token == null) {
            return Resource.Error("User not authenticated")
        }

        return try {
            val response = apiService.getDiaryEntries(token = "Bearer $token")
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error(response.message() ?: "Failed to retrieve entries")
            }
        } catch (e: HttpException) {
            Resource.Error(e.localizedMessage ?: "An unexpected HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Couldn\\'t reach server. Check your internet connection.")
        }
    }

    // Add functions for updating, deleting, or getting specific entries later
}

