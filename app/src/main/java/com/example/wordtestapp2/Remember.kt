package com.example.wordtestapp2

import io.realm.kotlin.types.RealmObject

open class Remember : RealmObject {
    open var japanese: String = ""
    open var english: String = ""
    open var example: String = ""
    open var group: String = ""
}