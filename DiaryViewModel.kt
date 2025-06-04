// Android Client Prototype - ui/viewmodel/DiaryViewModel.kt
package com.example.diarydepresiku.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diarydepresiku.data.network.DiaryEntriesListResponse
import com.example.diarydepresiku.data.network.DiaryEntryDto
import com.example.diarydepresiku.data.network.DiaryEntryResponse
import com.example.diarydepresiku.data.repository.DiaryRepository
import com.example.diarydepresiku.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for handling diary entry related logic (creating, fetching)
 * and managing diary-related UI state.
 */
@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryRepository: DiaryRepository
) : ViewModel() {

    // --- Create Entry State ---
    private val _createEntryState = MutableStateFlow<Resource<DiaryEntryResponse>?>(null)
    val createEntryState: StateFlow<Resource<DiaryEntryResponse>?> = _createEntryState.asStateFlow()

    /**
     * Attempts to create a new diary entry.
     * Updates the _createEntryState Flow with the result.
     */
    fun createDiaryEntry(emotionCategory: String?, emotionIntensity: Int?, notes: String?) {
        viewModelScope.launch {
            _createEntryState.value = Resource.Loading()
            val result = diaryRepository.createDiaryEntry(emotionCategory, emotionIntensity, notes)
            _createEntryState.value = result
        }
    }

    /**
     * Resets the create entry state.
     */
    fun resetCreateEntryState() {
        _createEntryState.value = null
    }

    // --- Get Entries State ---
    // Using a simple StateFlow for the list for this prototype.
    // For production, consider Paging 3 library for efficient list loading.
    private val _diaryEntriesState = MutableStateFlow<Resource<List<DiaryEntryDto>>>(Resource.Loading())
    val diaryEntriesState: StateFlow<Resource<List<DiaryEntryDto>>> = _diaryEntriesState.asStateFlow()

    /**
     * Fetches all diary entries for the current user.
     * Updates the _diaryEntriesState Flow.
     */
    fun fetchDiaryEntries() {
        viewModelScope.launch {
            _diaryEntriesState.value = Resource.Loading() // Indicate loading
            when (val result = diaryRepository.getDiaryEntries()) {
                is Resource.Success -> {
                    _diaryEntriesState.value = Resource.Success(result.data?.entries ?: emptyList())
                }
                is Resource.Error -> {
                    _diaryEntriesState.value = Resource.Error(result.message ?: \"Failed to load entries\")
                }
                else -> { /* Already loading */ }
            }
        }
    }

    // Call fetchDiaryEntries initially or when needed (e.g., screen appears)
    // init {
    //     fetchDiaryEntries()
    // }
}

