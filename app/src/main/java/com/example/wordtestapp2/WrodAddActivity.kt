package com.example.wordtestapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContentResolverCompat.query
import com.google.android.material.snackbar.Snackbar
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults

class WrodAddActivity : AppCompatActivity() {

    //val realm: Realm = Realm.getDefaultInstance()　の最新版の書き方　14～16
    val config = RealmConfiguration.Builder(schema = setOf(Remember::class))
        .build()
    val realm: Realm = Realm.open(config)

    val saveButton: Button = findViewById(R.id.saveButton)

    val japaneseEditText:EditText = findViewById(R.id.japaneseEditText)
    val englishEditText:EditText = findViewById(R.id.englishEditText)
    val exampleEditText:EditText = findViewById(R.id.exampleEditText)

    val groupSpinner:Spinner = findViewById(R.id.groupSpinner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrod_add)

        val spinner: Spinner = findViewById(R.id.groupSpinner)

        ArrayAdapter.createFromResource(
            this,
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        //　変数:○○　は変数の中に○○しか入れられない
        //rememberに入力した単語のデータが入っている
        val remember: RealmResults<Remember>? = readAll()

        var groupAlf: String = "None"

        // リスナーを登録
        groupSpinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            //　アイテムが選択された時
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinner = parent
                groupAlf = spinner?.selectedItem.toString()
            }

            //　アイテムが選択されなかった
            override fun onNothingSelected(parent: AdapterView<*>?) {
                //
            }
        })

        saveButton.setOnClickListener {
            val japanese: String = japaneseEditText.text.toString()
            val english: String = englishEditText.text.toString()
            val example: String = exampleEditText.text.toString()


            save(japanese, english, example, groupAlf)
        }
    }

    private fun readAll(): RealmResults<Remember>? {
        val rememberResult: RealmResults<Remember> = realm.query<Remember>().find()
        return rememberResult
    }

    private fun save(Sjapanese: String, Senglish: String, Sexample: String, groupAlf: String) {

        //update
        realm.writeBlocking {
            val REmember:Remember? = query<Remember>()
                .query("english == Senglish")
                .first()
                .find()
        }

        //new create
        realm.writeBlocking {
            copyToRealm(Remember().apply {
                japanese = Sjapanese
                english = Senglish
                example = Sexample
                group = groupAlf
            })
        }

//        realm.writeBlocking  {
//
//            copyToRealm(Remember().apply{
//
//                if (remember != null) {
//                remember.japanese = japanese
//                remember.english = english
//                remember.example = example
//                remember.group = groupAlf
//                } else {
//                val newRemember: Remember = it.createObject(Remember::class.java)
//                newRemember.japanese = japanese
//                newRemember.english = english
//                newRemember.example = example
//                newRemember.group = groupAlf
//            }
//            })
//        }
//        Snackbar.make(container, "保存しました", Snackbar.LENGTH_SHORT).show()
//    }



}

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}