package com.example.wordtestapp2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

interface SaveAsFavorite{

    fun buttonTapped()
}

class RecyclerAdapter:RecyclerView.Adapter<ViewHolderItem>() {

    val nameList = listOf("動詞","名詞","形容詞")
    val allOfCorrectList= listOf("72/102","9/45","46/58")

//    val imageList = listOf(
//        R.drawable.cake_image,R.drawable.cat_image,
//    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {

        val itemXml = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_list_layout,parent,false)
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