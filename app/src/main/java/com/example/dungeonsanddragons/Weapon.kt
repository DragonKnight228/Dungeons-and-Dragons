package com.example.dungeonsanddragons

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import com.example.dungeonsanddragons.databinding.FragmentWeaponBinding
import java.text.FieldPosition


class Weapon : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeaponBinding.inflate(inflater, container, false)

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

        var recyclerView: RecyclerView? = view?.findViewById(R.id.recycler_view)
        if (recyclerView != null) {
            recyclerView.layoutManager = LinearLayoutManager(this.context)
        }

        val field_name = view?.findViewById<View>(R.id.linearLayout)
        if (field_name != null) {
            field_name.setBackgroundResource(R.drawable.shape_for_field)
        }
        return binding.root    }

}

class CustomRecyclerViewAdapter(){
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var weapon_name = itemView.findViewById<TextView>(R.id.weapon_name)
        var damage = itemView.findViewById<TextView>(R.id.damage)
        var bonus = itemView.findViewById<TextView>(R.id.bonus)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.weapon_field, parent, false)
        return CustomViewHolder(itemView)
    }

    fun onBindViewHolder(holder: CustomViewHolder, position: FieldPosition){
        holder.weapon_name.text = "Пупа"
    }

    fun getItemCount(): Int {
        TODO()
    }
}