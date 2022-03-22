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
        var binding = FragmentCharacteristicsBinding.inflate(inflater, container, false)
        binding.fieldName.name.text = getString(R.string.name)
        binding.fieldLevel.name.text = getString(R.string.level)
        binding.fieldRace.name.text = getString(R.string.race)
        binding.fieldXp.name.text = getString(R.string.xp)

        val field_name = view?.findViewById<View>(R.id.linearLayout)
        if (field_name != null) {
            field_name.setBackgroundResource(R.drawable.shape_for_field)
        }
        return binding.root
    }


}