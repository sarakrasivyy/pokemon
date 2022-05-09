package com.example.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Adapter
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {


    private lateinit var binding: ActivityMainBinding
    private lateinit var adarter: DogAdarter
    private val dogimages = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.dogsi.setOnQueryTextListener(this)
        initRecylerView()
    }

    private fun initRecylerView() {
        adarter = DogAdarter(dogimages)
        binding.rcdoggi.layoutManager = LinearLayoutManager(this)
        binding.rcdoggi.adapter = adarter
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searbyname(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(Apiservice::class.java).getDogbyraza("$query/images")
            val pupis = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val images: List<String> = pupis?.perritos ?: emptyList()
                    dogimages.clear()
                    dogimages.addAll(images)
                    adarter.notifyDataSetChanged()


                } else {
                    showError()
                }
            }
        }
    }

    private fun showError() {
        Toast.makeText(this, "ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (!p0.isNullOrEmpty()){
            searbyname(p0.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
    return true
    }
}