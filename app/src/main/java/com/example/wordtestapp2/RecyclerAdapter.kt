package com.example.wordtestapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolderItem>(){

    inner class ViewHolderItem(itemView: View, recyclerView: RecyclerAdapter) :
            RecyclerView.ViewHolder(itemView){
                private val config = RealmConfiguration.Builder(schema = setOf(Remember::class)).build()
                private val realm:Realm = Realm.open(config)
                val correctJapanese: TextView = itemView.findViewById(R.id.correct_japanese)
                val correctEnglish: TextView = itemView.findViewById(R.id.correct_english)

                init {
                    //一行分のアイテムを選択したとき
                    itemView.setOnClickListener {
                        val position:Int = adapterPosition
                        val listPosition = wordList[position]

                        fun delete(){
                            //     ↓これはrealm.writeBlocking{}の｛｝内で「自分でまとめたdata class（今回はRemember class）を扱って何かします.」と伝えるためにある。
                            realm.writeBlocking {
                                val deleteList = this.query<Remember>().find().first()
                                delete(deleteList)
                            }
                        }
                    }
                }
            }
//            　　　　　　　　　　　　　　　↓これ（型を定義）で保存してる場所とつながった
    private val wordList:MutableList<Remember> = mutableListOf()

    //一行分のリストを用意
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderItem {
        val itemXml = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_list_layout,parent,false)
        return ViewHolderItem(itemXml,this)
    }

    //リストのposition番目を取得
    override fun onBindViewHolder(holder: ViewHolderItem, position: Int) {

        //override fun onBindViewHolderは存在するだけで中身は書かなくてもいい時はある。
        //今回は一覧画面で「選択して消す」でどこ（position）を押したかの情報が必要だから記述する。

        // Rememberのどこと対応するかを「.○○」で明示した
        val japaneseItem = wordList[position].japanese
        //holder: ViewHolderItemより、holderはViewHolderItemが型である。そのためViewHolderItemの中にあるcorrect○○を引っ張てこれる。
        //また、ViewHolderItem.correct○○にできない理由は、あくまでViewHolderItemはStringやIntと同じの種類の”型”に過ぎないから。
        holder.correctJapanese.text = japaneseItem

        val englishItem = wordList[position].english
        holder.correctEnglish.text = englishItem
    }
    //リストのサイズ
    override fun getItemCount(): Int {
        return wordList.size
    }

    //　ここまでの3個のひな形で書いたことをwordListというmutableListOf()に渡す関数
    //　　　　　　　　　↓「addListはRememberという型のListの型だよ」
    fun submitList(addList:List<Remember>){
        wordList.addAll(addList)
        notifyDataSetChanged()
    }
}