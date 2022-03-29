package com.example.dungeonsanddragons

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding


class Characteristics : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCharacteristicsBinding.inflate(inflater, container, false)
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


        val field_name = view?.findViewById<View>(R.id.linearLayout)
        if (field_name != null) {
            field_name.setBackgroundResource(R.drawable.shape_for_field)
        }
        return binding.root
    }


}