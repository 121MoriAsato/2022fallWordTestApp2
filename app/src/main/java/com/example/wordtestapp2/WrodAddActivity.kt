package com.example.wordtestapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.read
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContentResolverCompat.query
import com.example.wordtestapp2.databinding.ActivityMainBinding
import com.example.wordtestapp2.databinding.ActivityWrodAddBinding
import com.google.android.material.snackbar.Snackbar
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class WrodAddActivity : AppCompatActivity() {

    private lateinit var binding:ActivityWrodAddBinding

    //val realm: Realm = Realm.getDefaultInstance()　の最新版の書き方　14～16
    val config = RealmConfiguration.Builder(schema = setOf(Remember::class))
        .build()
    val realm: Realm = Realm.open(config)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWrodAddBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.quizButton.setOnClickListener {
            val quizIntent =Intent(this,MainActivity::class.java)
            startActivity(quizIntent)
        }

        binding.returnListButton.setOnClickListener {
            val listIntent =Intent(this,WordListActvity::class.java)
            startActivity(listIntent)
        }





        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }



       // var groupAlf: String = "None"



//        // リスナーを登録
//        binding.groupSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
//            //　アイテムが選択された時
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val spinner = parent
//                groupAlf = spinner?.selectedItem.toString()
//            }
//
//            //　アイテムが選択されなかった
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                //
//            }
//        })

        binding.saveButton.setOnClickListener {
            val etJapanese: String = binding.japaneseEditText.text.toString()
            val etEnglish: String = binding.englishEditText.text.toString()
           // val example: String = binding.exampleEditText.text.toString()

            //save(japanese, english, example, groupAlf)
            save(etJapanese, etEnglish)
        }
    }

    private fun save(wdJapanese: String, wdEnglish: String) {

        //　変数:○○　は変数の中に○○しか入れられない
        //rememberに入力した単語のデータが入っている
        val remember: Remember? = readAll()

        //ありこさんと書き方が違う
        remember.japanese = wdJapanese
        remember.english = wdEnglish
    }

    private fun readAll(): Remember? {
        //: RealmResults<Remember>は「今からRememberの中から検索しますよ～」と伝えている。
        // 　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　↓queryはRememberの中の何かを（）内に書いて持ってくる
        val rememberResult: RealmResults<Remember> = realm.query<Remember>().find()
        return Remember()
    }

    //頭文字が大文字なのを治す
    private fun save(Sjapanese: String, Senglish: String, Sexample: String, groupAlf: String) {

        //update
//        realm.writeBlocking {
//            val REmember:Remember? = query<Remember>()
//                .query("english == Senglish")
//                .first()
//                .find()
//        }

        //new create
        realm.writeBlocking {
            copyToRealm(Remember().apply {
                japanese = Sjapanese
                english = Senglish
                example = Sexample
                group = groupAlf
            })
        }


}

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}