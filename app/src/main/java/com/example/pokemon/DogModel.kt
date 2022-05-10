package com.example.pokemon

import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogModel {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    suspend fun getDogs(query: String, response: (DogResponse) -> Unit) {
        coroutineScope {
            val call = getRetrofit().create(Apiservice::class.java).getDogbyraza("$query/images")
            call.body().let {
                if (call.isSuccessful) {
                    if (it != null) {
                        response(DogResponse.OnSuccess(it.perritos))
                    } else {
                        response(DogResponse.OnError("ha ocurrido un error"))
                    }
                } else {
                    response(DogResponse.OnError("ha ocurrido un error -> ${call.message()}"))
                }
            }
        }
    }
}