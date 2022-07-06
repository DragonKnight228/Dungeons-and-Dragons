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

    var athletic: Boolean = false
    var acrobatic: Boolean = false
    var handDexterity: Boolean = false
    var steals: Boolean = false
    var magic: Boolean = false
    var history: Boolean = false
    var investigation: Boolean = false
    var religion: Boolean = false
    var insight: Boolean = false
    var medicine: Boolean = false
    var nature: Boolean = false
    var perception: Boolean = false
    var survival: Boolean = false
    var animals: Boolean = false
    var deception: Boolean = false
    var fear: Boolean = false
    var performance: Boolean = false
    var belief: Boolean = false
}