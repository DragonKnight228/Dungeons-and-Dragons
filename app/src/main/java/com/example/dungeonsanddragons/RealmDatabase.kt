package com.example.dungeonsanddragons

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DatabaseCharacter: RealmObject() {
    @PrimaryKey
    var character_id: Int = 0

    var character_name: String = ""
    var character_level: String = ""
}