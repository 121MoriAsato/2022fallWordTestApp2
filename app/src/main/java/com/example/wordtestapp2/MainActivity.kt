package com.example.wordtestapp2

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class
MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Addword = findViewById<Button>(R.id.AddWordButton)

        Addword.setOnClickListener {
            val toAddActivityIntent = Intent(this,WrodAddActivity::class.java)
            startActivity(toAddActivityIntent)

//            Addword.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
        }

        recyclerView = findViewById(R.id.rv)
        recyclerView.adapter = RecyclerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}