package com.example.wordtestapp2

import io.realm.kotlin.types.RealmObject

class Remember :RealmObject{
     var japanese: String = ""
     var english: String = ""
     var example: String = ""
     var group: String = ""
}