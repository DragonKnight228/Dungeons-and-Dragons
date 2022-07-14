package com.example.dungeonsanddragons

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import com.example.dungeonsanddragons.databinding.FragmentWeaponBinding
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import java.text.FieldPosition


class Weapon : Fragment() {


    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration
    var current_character: DatabaseCharacter? = DatabaseCharacter()
    private var current_character_id = 1
    lateinit var bind_object: FragmentWeaponBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentWeaponBinding.inflate(inflater, container, false)

        bind_object = binding
        val character_activity = activity as Character
        character_activity.bind_from_fragment_weapon = binding

        current_character_id = (activity?.intent?.extras?.getInt("character_id") ?: context?.let { Realm.init(it) }) as Int
        configuration = RealmConfiguration.Builder().name("Characters database").deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).build()
        ourRealm = Realm.getInstance(configuration)
        current_character = ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findFirst()

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
        binding.initiativeField.name1.textSize =
            resources.getDimension(R.dimen.alternative_font_size)

        val recyclerView: RecyclerView? = view?.findViewById(R.id.recycler_view)
        if (recyclerView != null) {
            recyclerView.layoutManager =
                LinearLayoutManager(this.context)
        }

        view?.findViewById<View>(R.id.linearLayout)?.setBackgroundResource(R.drawable.shape_for_field)
        return binding.root
    }

    override fun onResume(){
        super.onResume()
        ourRealm.executeTransaction {
            bind_object.kdField.inputText1.setText(current_character?.character_kd)
            bind_object.hpField.inputText1.setText(current_character?.character_hits)
            bind_object.hpDiceField.inputText1.setText(current_character?.character_kh)
            bind_object.speedField.inputText1.setText(current_character?.character_speed)
            bind_object.maxHpField.inputText1.setText(current_character?.character_max_hits)
            bind_object.initiativeField.inputText1.setText(current_character?.character_initiative)
        }
    }

}