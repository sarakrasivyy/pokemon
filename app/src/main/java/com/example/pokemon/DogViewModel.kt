package com.example.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DogViewModel : ViewModel() {

    private val dogModel = DogModel()
    private val getDogsMutableLiveData: MutableLiveData<RequestStatusDogs<List<String>>> =
        MutableLiveData()

    fun getDogsLiveData(): LiveData<RequestStatusDogs<List<String>>> = getDogsMutableLiveData

    fun getImagesDogByRaza(query: String) {
        getDogsMutableLiveData.value = RequestStatusDogs.OnLoading
        viewModelScope.launch {
            dogModel.getDogs(query){ dogResponse: DogResponse ->
                when (dogResponse){
                    is DogResponse.OnSuccess<*> -> {
                        getDogsMutableLiveData.postValue(
                            RequestStatusDogs.OnSuccess(dogResponse.data as List<String>)
                        )
                    }
                    is DogResponse.OnError -> {
                        getDogsMutableLiveData.postValue(
                            RequestStatusDogs.OnError(dogResponse.error)
                        )

                    }
                }
            }
        }
    }
}