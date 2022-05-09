package com.example.pokemon

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface Apiservice {
    @GET
 suspend   fun getDogbyraza(@Url url: String):Response<Dogsie>
}