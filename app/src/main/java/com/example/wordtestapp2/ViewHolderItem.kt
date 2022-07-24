package com.example.wordtestapp2

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderItem(itemView:View):RecyclerView.ViewHolder(itemView) {
    val groupNameViewHolder: TextView = itemView.findViewById(R.id.groupNameView)
    val allOfCorrectwordViewHolder:TextView = itemView.findViewById(R.id.allOfCorrectwordView)
    val allWordButtonHolder:Button = itemView.findViewById(R.id.allWordButton)
    val favoriteButtonHolder:Button = itemView.findViewById(R.id.favoriteButton)
}