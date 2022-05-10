package com.example.pokemon

sealed class RequestStatusDogs<out T> {
    object OnLoading : RequestStatusDogs<Nothing>()
    class OnSuccess<T>(val data: T) : RequestStatusDogs<T>()
    class OnError(val error: String) : RequestStatusDogs<Nothing>()
}