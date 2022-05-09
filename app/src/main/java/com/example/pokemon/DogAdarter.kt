package com.example.pokemon

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DogAdarter(val images:List<String>):RecyclerView.Adapter<DogViewHol>(){




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHol {
val LayoutInflater= LayoutInflater.from(parent.context)
        return DogViewHol(LayoutInflater.inflate(R.layout.item_2, parent, false))
 }

    override fun onBindViewHolder(holder: DogViewHol, position: Int) {

        val item: String= images[position]
        holder.bing(item)

    }

    override fun getItemCount(): Int = images.size

}
