// Android Client Prototype - di/AppModule.kt
package com.example.diarydepresiku.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.diarydepresiku.data.local.UserPreferences // Assuming UserPreferences exists
import com.example.diarydepresiku.data.network.ApiService
import com.example.diarydepresiku.data.repository.AuthRepository
import com.example.diarydepresiku.data.repository.DiaryRepository
import com.example.diarydepresiku.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Define the DataStore instance
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

/**
 * Dagger Hilt Module to provide application-wide dependencies.
 */
@Module
@InstallIn(SingletonComponent::class) // Dependencies live as long as the application
object AppModule {

    /**
     * Provides the Retrofit instance for network communication.
     */
    @Provides
    @Singleton // Ensure only one instance is created
    fun provideRetrofit(): Retrofit {
        // Add logging interceptor for debugging network requests (optional)
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            // Add Auth Interceptor here if needed to automatically add token
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON parsing
            .build()
    }

    /**
     * Provides the ApiService instance created by Retrofit.
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides the DataStore<Preferences> instance.
     */
    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    /**
     * Provides the UserPreferences instance for managing local preferences.
     */
    @Provides
    @Singleton
    fun provideUserPreferences(dataStore: DataStore<Preferences>): UserPreferences {
        // Create UserPreferences class to handle DataStore operations
        return UserPreferences(dataStore)
    }

    /**
     * Provides the AuthRepository instance.
     * Depends on ApiService and UserPreferences.
     */
    @Provides
    @Singleton
    fun provideAuthRepository(apiService: ApiService, userPreferences: UserPreferences): AuthRepository {
        // Create AuthRepository class
        return AuthRepository(apiService, userPreferences)
    }

    /**
     * Provides the DiaryRepository instance.
     * Depends on ApiService and UserPreferences (to get token).
     */
    @Provides
    @Singleton
    fun provideDiaryRepository(apiService: ApiService, userPreferences: UserPreferences): DiaryRepository {
        // Create DiaryRepository class
        return DiaryRepository(apiService, userPreferences)
    }

    // Provide Room Database and DAOs here later if local caching is implemented
    /*
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "diary_database"
        ).build()
    }

    @Provides
    fun provideDiaryEntryDao(appDatabase: AppDatabase): DiaryEntryDao {
        return appDatabase.diaryEntryDao()
    }
    */
}

