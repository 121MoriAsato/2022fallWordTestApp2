package com.example.wordtestapp2

import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ViewHolderItem(itemView:View):RecyclerView.ViewHolder(itemView) {
    val groupNameViewHolder: TextView = itemView.findViewById(R.id.groupNameView)
    val allOfCorrectwordViewHolder:TextView = itemView.findViewById(R.id.allOfCorrectwordView)
    val favoriteButtonHolder:Button = itemView.findViewById(R.id.favoriteButton)

    val recyclerAdapter = RecyclerAdapter()
    val namelist = recyclerAdapter.nameList

    init {
        itemView.setOnClickListener {
            val position:Int = adapterPosition
            //intentで問題画面に飛ぶ

            //実験用：Toast.makeText(itemView.context,namelist[position], Toast.LENGTH_SHORT).show()
        }

        itemView.setOnLongClickListener {
            //intentでそのグループに追加してる単語の一覧を表示
        }
    }
}