package com.example.pokemon

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.Item2Binding
import com.squareup.picasso.Picasso

class DogViewHol(view: View):RecyclerView.ViewHolder(view) {

    private val binding= Item2Binding.bind(view)

    fun bing (image:String){
        Picasso.get().load(image).into(binding.ivdogini)

    }
}