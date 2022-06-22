package com.example.dungeonsanddragons

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
        configuration = RealmConfiguration.Builder().name("Characters database").allowWritesOnUiThread(true).build()
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
        }

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