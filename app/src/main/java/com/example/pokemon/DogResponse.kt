package com.example.pokemon

sealed class DogResponse {
    class OnSuccess<T>(val data: T) : DogResponse()
    class OnError(val error: String) : DogResponse()
}