package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private val dogViewModel: DogViewModel by lazy {
        ViewModelProvider(this)[DogViewModel::class.java]
    }
    /*private lateinit var dogViewModel: DogViewModel*/


    private lateinit var binding: ActivityMainBinding
    private lateinit var adarter: DogAdarter
    private val dogimages = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //dogViewModel = ViewModelProvider(this)[DogViewModel::class.java]
        setContentView(binding.root)
        binding.dogsi.setOnQueryTextListener(this)
        initRecylerView()


        dogViewModel.getDogsLiveData().observe(this){ requestStatusDogs: RequestStatusDogs<List<String>> ->
            when (requestStatusDogs){
                is RequestStatusDogs.OnLoading -> {
                    showStatusMessage("trayendo perritus")

                }
                is RequestStatusDogs.OnSuccess -> {
                    dogimages.clear()
                    dogimages.addAll(requestStatusDogs.data)
                    adarter.notifyDataSetChanged()

                }
                is RequestStatusDogs.OnError -> {
                    showStatusMessage(requestStatusDogs.error)
                }
            }
        }
    }

    private fun initRecylerView() {
        adarter = DogAdarter(dogimages)
        binding.rcdoggi.layoutManager = LinearLayoutManager(this)
        binding.rcdoggi.adapter = adarter
    }
    private fun showStatusMessage(mesage: String) {
        Toast.makeText(this, mesage, Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (!p0.isNullOrEmpty()){
            dogViewModel.getImagesDogByRaza(p0)
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
    return true
    }
}