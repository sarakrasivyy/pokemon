package com.example.pokemon

import com.google.gson.annotations.SerializedName

data class Dogsie(@SerializedName("status") var status: String,
@SerializedName("message") var perritos: List<String>)