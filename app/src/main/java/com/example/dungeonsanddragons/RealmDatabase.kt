package com.example.dungeonsanddragons

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class DatabaseCharacter: RealmObject() {
    @PrimaryKey
    var character_id: Int = 0

    var character_name: String = ""
}