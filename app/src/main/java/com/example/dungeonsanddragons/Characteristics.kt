package com.example.dungeonsanddragons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where


class Characteristics: Fragment() {


    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration

    var current_character: DatabaseCharacter? = DatabaseCharacter()
    private var current_character_id = 1
    lateinit var bind_object: FragmentCharacteristicsBinding

    var athletic_modifier = 0
    var acrobatic_modifier = 0
    var handDexterity_modifier  = 0
    var steals_modifier = 0
    var magic_modifier = 0
    var history_modifier = 0
    var investigation_modifier = 0
    var religion_modifier = 0
    var insight_modifier = 0
    var medicine_modifier = 0
    var nature_modifier = 0
    var perception_modifier = 0
    var survival_modifier = 0
    var animals_modifier = 0
    var deception_modifier = 0
    var fear_modifier = 0
    var performance_modifier = 0
    var belief_modifier = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentCharacteristicsBinding.inflate(inflater, container, false)
        bind_object = binding
        val character_activity = activity as Character
        character_activity.bind_from_fragment_characteristics = binding

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

    fun getAbilityModifier(characteristic_modifier: Int, ability_checked: Boolean?): Int{
        if (ability_checked == true) return (characteristic_modifier + 2)
        else return characteristic_modifier
    }



    fun bindingText(binding: FragmentCharacteristicsBinding){

        ourRealm.executeTransaction{
            bind_object.fieldName.inputText.setText(current_character?.character_name)
            bind_object.fieldLevel.inputText.setText(current_character?.character_level)
            bind_object.fieldRace.inputText.setText(current_character?.character_race)
            bind_object.fieldXp.inputText.setText(current_character?.character_xp)

            bind_object.strengthField.characteristicInputText.setText(current_character?.character_strength)
            val strength_modifier = getModifier(current_character, "strengh")
            bind_object.strengthField.characteristicModifier.text = strength_modifier.toString()

            bind_object.athleticField.abilityField.isChecked = current_character?.athletic == true
            athletic_modifier = getAbilityModifier(strength_modifier, current_character?.athletic)

            bind_object.dexterityField.characteristicInputText.setText(current_character?.character_dexterity)
            val dexterity_modifier = getModifier(current_character, "dexterity")
            bind_object.dexterityField.characteristicModifier.text = dexterity_modifier.toString()

            bind_object.acrobaticField.abilityField.isChecked = current_character?.acrobatic == true
            acrobatic_modifier = getAbilityModifier(dexterity_modifier, current_character?.acrobatic)

            bind_object.handDexterityField.abilityField.isChecked = current_character?.handDexterity == true
            handDexterity_modifier = getAbilityModifier(dexterity_modifier, current_character?.handDexterity)

            bind_object.stealsField.abilityField.isChecked = current_character?.steals == true
            steals_modifier = getAbilityModifier(dexterity_modifier, current_character?.steals)

            bind_object.constitutionField.characteristicInputText.setText(current_character?.character_constitution)
            val constitution_modifier = getModifier(current_character, "constitution")
            bind_object.constitutionField.characteristicModifier.text = constitution_modifier.toString()

            bind_object.intelligenceField.characteristicInputText.setText(current_character?.character_intelligence)
            val intelligence_modifier = getModifier(current_character, "intelligence")
            bind_object.intelligenceField.characteristicModifier.text = intelligence_modifier.toString()

            bind_object.magicField.abilityField.isChecked = current_character?.magic == true
            magic_modifier = getAbilityModifier(intelligence_modifier, current_character?.magic)

            bind_object.historyField.abilityField.isChecked = current_character?.history == true
            history_modifier = getAbilityModifier(intelligence_modifier, current_character?.history)

            bind_object.investigationField.abilityField.isChecked = current_character?.investigation == true
            investigation_modifier = getAbilityModifier(intelligence_modifier, current_character?.investigation)

            bind_object.religionField.abilityField.isChecked = current_character?.religion == true
            religion_modifier = getAbilityModifier(intelligence_modifier, current_character?.religion)

            bind_object.wisdomField.characteristicInputText.setText(current_character?.character_wisdom)
            val wisdom_modifier = getModifier(current_character, "wisdom")
            bind_object.wisdomField.characteristicModifier.text = wisdom_modifier.toString()

            bind_object.insightField.abilityField.isChecked = current_character?.insight == true
            insight_modifier = getAbilityModifier(wisdom_modifier, current_character?.insight)

            bind_object.medicineField.abilityField.isChecked = current_character?.medicine == true
            medicine_modifier = getAbilityModifier(wisdom_modifier, current_character?.medicine)

            bind_object.natureField.abilityField.isChecked = current_character?.nature == true
            nature_modifier = getAbilityModifier(wisdom_modifier, current_character?.nature)

            bind_object.perceptionField.abilityField.isChecked = current_character?.perception == true
            perception_modifier = getAbilityModifier(wisdom_modifier, current_character?.perception)

            bind_object.survivalField.abilityField.isChecked = current_character?.survival == true
            survival_modifier = getAbilityModifier(wisdom_modifier, current_character?.survival)

            bind_object.animalsField.abilityField.isChecked = current_character?.animals == true
            animals_modifier = getAbilityModifier(wisdom_modifier, current_character?.animals)

            bind_object.charismaField.characteristicInputText.setText(current_character?.character_charisma)
            val charisma_modifier = getModifier(current_character, "charisma")
            bind_object.charismaField.characteristicModifier.text = charisma_modifier.toString()

            bind_object.deceptionField.abilityField.isChecked = current_character?.deception == true
            deception_modifier = getAbilityModifier(intelligence_modifier, current_character?.deception)

            bind_object.fearField.abilityField.isChecked = current_character?.fear == true
            fear_modifier = getAbilityModifier(intelligence_modifier, current_character?.fear)

            bind_object.performanceField.abilityField.isChecked = current_character?.performance == true
            performance_modifier = getAbilityModifier(intelligence_modifier, current_character?.performance)

            bind_object.beliefField.abilityField.isChecked = current_character?.belief == true
            belief_modifier = getAbilityModifier(intelligence_modifier, current_character?.belief)
        }

        binding.fieldName.name.text = getString(R.string.name)
        binding.fieldLevel.name.text = getString(R.string.level)
        binding.fieldRace.name.text = getString(R.string.race)
        binding.fieldXp.name.text = getString(R.string.xp)


        binding.strengthField.nameForCharacteristic.text = getString(R.string.strength)

        binding.dexterityField.nameForCharacteristic.text = getString(R.string.dexterity)

        binding.constitutionField.nameForCharacteristic.text = getString(R.string.constitution)
        binding.constitutionField.nameForCharacteristic.textSize = resources.getDimension(R.dimen.constitution_font_size)

        binding.intelligenceField.nameForCharacteristic.text = getString(R.string.intelligence)

        binding.wisdomField.nameForCharacteristic.text = getString(R.string.wisdom)

        binding.charismaField.nameForCharacteristic.text = getString(R.string.charisma)

        binding.athleticField.abilityField.text = "Атлетика (сил) +$athletic_modifier"
        binding.acrobaticField.abilityField.text = "Акробатика (лов) +$acrobatic_modifier"
        binding.handDexterityField.abilityField.text = "Ловкость рук (лов) +$handDexterity_modifier"
        binding.stealsField.abilityField.text = "Скрытность (лов) +$steals_modifier"
        binding.magicField.abilityField.text = "Магия (инт) +$magic_modifier"
        binding.historyField.abilityField.text = "История (инт) +$history_modifier"
        binding.investigationField.abilityField.text = "Расследование (инт) +$investigation_modifier"
        binding.religionField.abilityField.text = "Религия (инт) +$religion_modifier"
        binding.insightField.abilityField.text = "Проницательность (муд) +$insight_modifier"
        binding.medicineField.abilityField.text = "Медицина (муд) +$medicine_modifier"
        binding.natureField.abilityField.text = "Природа (муд) +$nature_modifier"
        binding.perceptionField.abilityField.text = "Восприятие (муд) +$perception_modifier"
        binding.survivalField.abilityField.text = "Выживание (муд) +$survival_modifier"
        binding.animalsField.abilityField.text = "Обращение с животными (муд) +$animals_modifier"
        binding.deceptionField.abilityField.text = "Обман (хар) +$deception_modifier"
        binding.fearField.abilityField.text = "Запугивание (хар) +$fear_modifier"
        binding.performanceField.abilityField.text = "Выступление (хар) +$performance_modifier"
        binding.beliefField.abilityField.text = "Убеждение (хар) +$belief_modifier"


    }

}