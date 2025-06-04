// Android Client Prototype - util/Resource.kt
package com.example.diarydepresiku.util

/**
 * A generic class that holds a value with its loading status.
 * @param <T> Data type.
 */
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    /**
     * Represents a successful state with data.
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Represents an error state with an optional message and data.
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Represents a loading state, optionally with cached data.
     */
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

