package com.example.wordtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordtestapp2.databinding.ActivityMainBinding

class
MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //val addWord = findViewById<Button>(R.id.add_word_button)


        binding.addWordButton.setOnClickListener {
            val toAddActivityIntent = Intent(this,WrodAddActivity::class.java)
            startActivity(toAddActivityIntent)

//            Addword.setBackgroundTintList(ColorStateList.valueOf(Color.YELLOW));
        }

        binding.listButton.setOnClickListener {

        }

      //  recyclerView = findViewById(R.id.rv)
        recyclerView.adapter = RecyclerAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}