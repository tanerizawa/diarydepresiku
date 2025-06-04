// Android Client Prototype - data/local/UserPreferences.kt
package com.example.diarydepresiku.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.diarydepresiku.util.Constants.PREF_AUTH_TOKEN
import com.example.diarydepresiku.util.Constants.PREF_USER_DISPLAY_NAME
import com.example.diarydepresiku.util.Constants.PREF_USER_EMAIL
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Manages user preferences using Jetpack DataStore.
 * Handles storing and retrieving authentication token and basic user info.
 */
@Singleton
class UserPreferences @Inject constructor(private val dataStore: DataStore<Preferences>) {

    // Define Preference Keys
    private object PreferencesKeys {
        val AUTH_TOKEN = stringPreferencesKey(PREF_AUTH_TOKEN)
        val USER_EMAIL = stringPreferencesKey(PREF_USER_EMAIL)
        val USER_DISPLAY_NAME = stringPreferencesKey(PREF_USER_DISPLAY_NAME)
    }

    /**
     * Flow to observe the authentication token.
     * Emits null if the token is not set or an error occurs.
     */
    val authToken: Flow<String?> = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            preferences -> preferences[PreferencesKeys.AUTH_TOKEN]
        }

    /**
     * Flow to observe user email.
     */
    val userEmail: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            preferences -> preferences[PreferencesKeys.USER_EMAIL]
        }

     /**
     * Flow to observe user display name.
     */
    val userDisplayName: Flow<String?> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map {
            preferences -> preferences[PreferencesKeys.USER_DISPLAY_NAME]
        }


    /**
     * Saves the authentication token and user info to DataStore.
     */
    suspend fun saveAuthData(token: String, email: String, displayName: String?) {
        dataStore.edit {
            preferences ->
            preferences[PreferencesKeys.AUTH_TOKEN] = token
            preferences[PreferencesKeys.USER_EMAIL] = email
            if (displayName != null) {
                preferences[PreferencesKeys.USER_DISPLAY_NAME] = displayName
            } else {
                preferences.remove(PreferencesKeys.USER_DISPLAY_NAME)
            }
        }
    }

    /**
     * Clears all authentication data (token and user info) from DataStore.
     * Typically used during logout.
     */
    suspend fun clearAuthData() {
        dataStore.edit {
            preferences ->
            preferences.remove(PreferencesKeys.AUTH_TOKEN)
            preferences.remove(PreferencesKeys.USER_EMAIL)
            preferences.remove(PreferencesKeys.USER_DISPLAY_NAME)
        }
    }
}

