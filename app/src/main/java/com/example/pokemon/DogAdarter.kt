package com.example.pokemon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.Item2Binding
import com.squareup.picasso.Picasso

class DogAdarter(val images: List<String>) : RecyclerView.Adapter<DogAdarter.DogViewHol>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHol {
        val LayoutInflater = LayoutInflater.from(parent.context)
        return DogViewHol(LayoutInflater.inflate(R.layout.item_2, parent, false))
    }

    override fun onBindViewHolder(holder: DogViewHol, position: Int) {

        val item: String = images[position]
        holder.bing(item)

    }

    override fun getItemCount(): Int = images.size

    inner class DogViewHol(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = Item2Binding.bind(view)

        fun bing(image: String) {
            Picasso.get()
                .load(image)
                .placeholder(R.drawable.loading)
                .into(binding.ivdogini)

        }
    }
}
