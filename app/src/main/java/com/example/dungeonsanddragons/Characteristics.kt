package com.example.dungeonsanddragons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.query


class Characteristics : Fragment() {

    lateinit var bind_object: FragmentCharacteristicsBinding
    lateinit var character_name: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacteristicsBinding.inflate(inflater, container, false)
        bind_object = binding
        bindingText(binding)

        view?.findViewById<View>(R.id.linearLayout)?.setBackgroundResource(R.drawable.shape_for_field)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val binding = bind_object
        bindingText(binding)
        val config = RealmConfiguration.Builder(schema = setOf(MyCharacter.MyCharacterCharacteristics::class)).build()
        val myRealm: Realm = Realm.open(config)

        myRealm.writeBlocking {
            copyToRealm(MyCharacter.MyCharacterCharacteristics().apply {
                name = binding.fieldName.inputText.text.toString()
                level = binding.fieldLevel.inputText.text.toString()
                race = binding.fieldRace.inputText.text.toString()
            })
        }
        val character_name_from_realm: RealmResults<MyCharacter.MyCharacterCharacteristics> = myRealm.query<MyCharacter.MyCharacterCharacteristics>().find()
        character_name = character_name_from_realm.toString()

        myRealm.close()
        bind_object.fieldName.inputText.setText(character_name)
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