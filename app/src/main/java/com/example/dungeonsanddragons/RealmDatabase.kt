package com.example.dungeonsanddragons

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DatabaseCharacter: RealmObject() {

    @PrimaryKey
    var character_id: Int = 0

    var character_name: String = ""
    var character_level: String = ""
    var character_race: String = ""
    var character_xp: String = ""

    var character_kd: String = ""
    var character_hits: String = ""
    var character_kh: String = ""
    var character_speed: String = ""
    var character_max_hits: String = ""
    var character_initiative: String = ""

    var character_strength: String = ""
    var character_dexterity: String = ""
    var character_constitution: String = ""
    var character_intelligence: String = ""
    var character_wisdom: String = ""
    var character_charisma: String = ""
}