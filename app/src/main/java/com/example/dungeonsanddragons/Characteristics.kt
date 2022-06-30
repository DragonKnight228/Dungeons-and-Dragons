package com.example.dungeonsanddragons

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.example.dungeonsanddragons.databinding.ActivityMainBinding
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmQuery
import io.realm.RealmResults
import io.realm.annotations.PrimaryKey
import io.realm.kotlin.where


class Characteristics: Fragment() {


    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration

    var current_character: DatabaseCharacter? = DatabaseCharacter()
    private var current_character_id = 1
    lateinit var bind_object: FragmentCharacteristicsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCharacteristicsBinding.inflate(inflater, container, false)
        bind_object = binding
        val character_activity = activity as Character
        character_activity.bind_from_fragment = binding
        bindingText(binding)

        current_character_id = (activity?.intent?.extras?.getInt("character_id") ?: context?.let { Realm.init(it) }) as Int
        configuration = RealmConfiguration.Builder().name("Characters database").deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).build()
        ourRealm = Realm.getInstance(configuration)
            current_character = ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findFirst()

        view?.findViewById<View>(R.id.linearLayout)?.setBackgroundResource(R.drawable.shape_for_field)
        return binding.root
    }


    override fun onResume() {
        super.onResume()
        bindingText(bind_object)
        ourRealm.executeTransaction{
            bind_object.fieldName.inputText.setText(current_character?.character_name)
            bind_object.fieldLevel.inputText.setText(current_character?.character_level)
            bind_object.fieldRace.inputText.setText(current_character?.character_race)
            bind_object.fieldXp.inputText.setText(current_character?.character_xp)
            
            bind_object.kdField.inputText1.setText(current_character?.character_kd)
            bind_object.hpField.inputText1.setText(current_character?.character_hits)
            bind_object.hpDiceField.inputText1.setText(current_character?.character_kh)
            bind_object.speedField.inputText1.setText(current_character?.character_speed)
            bind_object.maxHpField.inputText1.setText(current_character?.character_max_hits)
            bind_object.initiativeField.inputText1.setText(current_character?.character_initiative)

            bind_object.strengthField.characteristicInputText.setText(current_character?.character_strength)
            bind_object.strengthField.characteristicModifier.text = getModifier(current_character, "strengh").toString()

            bind_object.dexterityField.characteristicInputText.setText(current_character?.character_dexterity)
            bind_object.dexterityField.characteristicModifier.text = getModifier(current_character, "dexterity").toString()

            bind_object.constitutionField.characteristicInputText.setText(current_character?.character_constitution)
            bind_object.constitutionField.characteristicModifier.text = getModifier(current_character, "constitution").toString()

            bind_object.intelligenceField.characteristicInputText.setText(current_character?.character_intelligence)
            bind_object.intelligenceField.characteristicModifier.text = getModifier(current_character, "intelligence").toString()

            bind_object.wisdomField.characteristicInputText.setText(current_character?.character_wisdom)
            bind_object.wisdomField.characteristicModifier.text = getModifier(current_character, "wisdom").toString()

            bind_object.charismaField.characteristicInputText.setText(current_character?.character_charisma)
            bind_object.charismaField.characteristicModifier.text = getModifier(current_character, "charisma").toString()
        }

    }

    fun getModifier(current_character: DatabaseCharacter?, character_characteristic: String): Int {
        var modifier = 0

        when {
            character_characteristic == "strengh" -> {
                var strength_modifier = 0

                if (current_character?.character_strength == "") {
                    strength_modifier = 0
                }
                else {strength_modifier = (current_character?.character_strength?.toInt()?.minus(10)?.div(2)) ?: 2}
                modifier = strength_modifier
            }

            character_characteristic == "dexterity" -> {
                var dexterity_modifier = 0
                if (current_character?.character_dexterity == "") {
                    dexterity_modifier = 0
                } else dexterity_modifier =
                    (current_character?.character_dexterity?.toInt()?.minus(10)?.div(2)) ?: 0
                modifier = dexterity_modifier
            }

            character_characteristic == "constitution" -> {
                var constitution_modifier = 0
                if (current_character?.character_constitution == "") {
                    constitution_modifier = 0
                } else constitution_modifier =
                    (current_character?.character_constitution?.toInt()?.minus(10)?.div(2)) ?: 0
                modifier = constitution_modifier
            }

            character_characteristic == "intelligence" -> {
                var intelligence_modifier = 0
                if (current_character?.character_intelligence == "") {
                    intelligence_modifier = 0
                } else intelligence_modifier =
                    (current_character?.character_intelligence?.toInt()?.minus(10)?.div(2)) ?: 0
                modifier = intelligence_modifier
            }

            character_characteristic == "wisdom" -> {
                var wisdom_modifier = 0
                if (current_character?.character_wisdom == "") {
                    wisdom_modifier = 0
                } else {wisdom_modifier =
                    (current_character?.character_wisdom?.toInt()?.minus(10)?.div(2)) ?: 0}
                modifier = wisdom_modifier
            }

            character_characteristic == "charisma" -> {
                var charisma_modifier = 0
                if (current_character?.character_charisma == "") {
                    charisma_modifier = 0
                } else charisma_modifier =
                    (current_character?.character_charisma?.toInt()?.minus(10)?.div(2)) ?: 0
                modifier = charisma_modifier
            }
        }
        return modifier
    }



    fun bindingText(binding: FragmentCharacteristicsBinding){
        binding.fieldName.name.text = getString(R.string.name)
        binding.fieldLevel.name.text = getString(R.string.level)
        binding.fieldRace.name.text = getString(R.string.race)
        binding.fieldXp.name.text = getString(R.string.xp)

        binding.kdField.fullFieldComponent.setBackgroundResource(R.drawable.shield)
        binding.kdField.name1.text = getString(R.string.KD)

        binding.hpField.fullFieldComponent.setBackgroundResource(R.drawable.heart)
        binding.hpField.name1.text = getString(R.string.HP)

        binding.hpDiceField.fullFieldComponent.setBackgroundResource(R.drawable.hexagon)
        binding.hpDiceField.name1.text = getString(R.string.hp_dice)

        binding.speedField.name1.text = getString(R.string.speed)
        binding.speedField.name1.textSize = resources.getDimension(R.dimen.alternative_font_size)

        binding.maxHpField.name1.text = getString(R.string.max_hp)
        binding.maxHpField.name1.textSize = resources.getDimension(R.dimen.alternative_font_size)

        binding.initiativeField.name1.text = getString(R.string.initiative)
        binding.initiativeField.name1.textSize = resources.getDimension(R.dimen.alternative_font_size)

        binding.strengthField.nameForCharacteristic.text = getString(R.string.strength)

        binding.dexterityField.nameForCharacteristic.text = getString(R.string.dexterity)

        binding.constitutionField.nameForCharacteristic.text = getString(R.string.constitution)
        binding.constitutionField.nameForCharacteristic.textSize = resources.getDimension(R.dimen.constitution_font_size)

        binding.intelligenceField.nameForCharacteristic.text = getString(R.string.intelligence)

        binding.wisdomField.nameForCharacteristic.text = getString(R.string.wisdom)

        binding.charismaField.nameForCharacteristic.text = getString(R.string.charisma)

        binding.athleticField.abilityField.text = "Атлетика (сил)"
        binding.acrobaticField.abilityField.text = "Акробатика (лов)"
        binding.handDexterityField.abilityField.text = "Ловкость рук (лов)"
        binding.stealsField.abilityField.text = "Скрытность (лов)"
        binding.magicField.abilityField.text = "Магия (инт)"
        binding.historyField.abilityField.text = "История (инт)"
        binding.investigationField.abilityField.text = "Расследование (инт)"
        binding.religionField.abilityField.text = "Религия (инт)"
        binding.insightField.abilityField.text = "Проницательность (муд)"
        binding.medicineField.abilityField.text = "Медицина (муд)"
        binding.natureField.abilityField.text = "Природа (муд)"
        binding.perceptionField.abilityField.text = "Восприятие (муд)"
        binding.survivalField.abilityField.text = "Выживание (муд)"
        binding.animalsField.abilityField.text = "Обращение с животными (муд)"
        binding.deceptionField.abilityField.text = "Обман (хар)"
        binding.fearField.abilityField.text = "Запугивание (хар)"
        binding.performanceField.abilityField.text = "Выступление (хар)"
        binding.beliefField.abilityField.text = "Убеждение (хар)"
    }






}