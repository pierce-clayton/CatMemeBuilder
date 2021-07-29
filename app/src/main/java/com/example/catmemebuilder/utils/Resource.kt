package com.example.catmemebuilder.utils

sealed class Resource<out T : Any> {
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val exception: Exception?, val errorMsg : String) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}