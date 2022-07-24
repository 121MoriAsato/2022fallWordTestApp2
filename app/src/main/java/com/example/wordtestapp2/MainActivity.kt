package com.example.wordtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class
MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Addword = findViewById<Button>(R.id.AddWordButton)

        Addword.setOnClickListener {
            val toAddActivityIntent = Intent(this,WrodAddActivity::class.java)
            startActivity(toAddActivityIntent)
        }
    }
}