package com.example.dungeonsanddragons

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dungeonsanddragons.databinding.FragmentEquipmentBinding


class Equipment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEquipmentBinding.inflate(inflater, container, false)

        binding.mmMoney.nameMoney.text = getString(R.string.mm)

        binding.smMoney.nameMoney.text = getString(R.string.sm)

        binding.emMoney.nameMoney.text = getString(R.string.em)

        binding.zmMoney.nameMoney.text = getString(R.string.zm)

        binding.pmMoney.nameMoney.text = getString(R.string.pm)

        return binding.root
    }

}