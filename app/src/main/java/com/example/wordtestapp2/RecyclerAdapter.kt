package com.example.wordtestapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface SaveAsFavorite{
    fun buttonTapped()
}

class RecyclerAdapter:RecyclerView.Adapter<ViewHolderItem>() {


//    val imageList = listOf(
//        R.drawable.cake_image,R.drawable.cat_image,
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {

        val itemXml = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_list_layout,parent,false)
        return ViewHolderItem(itemXml)
    }

    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {
        holder.groupNameViewHolder.text = nameList[position]
        holder.allOfCorrectwordViewHolder.text = allOfCorrectList[position]
        holder.favoriteButtonHolder.setBackgroundResource(R.drawable.after_change_button)
    }

    override fun getItemCount(): Int {
        return nameList.size
    }
}