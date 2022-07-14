package com.example.dungeonsanddragons

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.findFragment
import androidx.viewpager.widget.PagerAdapter
import com.example.dungeonsanddragons.databinding.*
import io.realm.*
import io.realm.kotlin.where

class Character : AppCompatActivity() {


    var current_character: DatabaseCharacter? = DatabaseCharacter()
    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration
    var current_character_id = 0
    lateinit var pager: ViewPager
    lateinit var pagerAdapter: Character.SectionsPagerAdapter
    lateinit var binding: ActivityCharacterBinding
    lateinit var bind_from_fragment_characteristics: FragmentCharacteristicsBinding
    lateinit var bind_from_fragment_weapon: FragmentWeaponBinding
    lateinit var bind_from_fragment_equipment: FragmentEquipmentBinding
    lateinit var bind_from_fragment_magic: FragmentMagicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            current_character_id = bundle.getInt("character_id")
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.your_character))
        setSupportActionBar(toolbar)

        Realm.init(this)
        configuration = RealmConfiguration.Builder().name("Characters database").deleteRealmIfMigrationNeeded().allowWritesOnUiThread(true).build()
        ourRealm = Realm.getInstance(configuration)
        current_character =
            ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findFirst()

        pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        pager = findViewById(R.id.pager)
        pager.adapter = pagerAdapter


        val tabLayout: TabLayout = findViewById(R.id.tablayout)
        tabLayout.setupWithViewPager(pager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.user)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.sword)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.backpack)
        tabLayout.getTabAt(3)?.setIcon(R.drawable.magic_wand)
}

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.character_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_character_button -> {
                val characters_list: RealmResults<DatabaseCharacter>? = ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findAll()
                ourRealm.executeTransaction {
                    if (characters_list != null) {
                        characters_list.deleteAllFromRealm()
                    }
                }
                onBackPressed()
                return true}

            R.id.save_character_button -> {
                val saving_character: DatabaseCharacter? = ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findFirst()
                ourRealm.executeTransaction {
                    if (saving_character != null) {
                        saving_character.character_name = bind_from_fragment_characteristics.fieldName.inputText.text.toString()
                        saving_character.character_level = bind_from_fragment_characteristics.fieldLevel.inputText.text.toString()
                        saving_character.character_race = bind_from_fragment_characteristics.fieldRace.inputText.text.toString()
                        saving_character.character_xp = bind_from_fragment_characteristics.fieldXp.inputText.text.toString()


                        saving_character.character_kd = bind_from_fragment_weapon.kdField.inputText1.text.toString()
                        saving_character.character_hits = bind_from_fragment_weapon.hpField.inputText1.text.toString()
                        saving_character.character_kh = bind_from_fragment_weapon.hpDiceField.inputText1.text.toString()
                        saving_character.character_speed = bind_from_fragment_weapon.speedField.inputText1.text.toString()
                        saving_character.character_max_hits = bind_from_fragment_weapon.maxHpField.inputText1.text.toString()
                        saving_character.character_initiative = bind_from_fragment_weapon.initiativeField.inputText1.text.toString()


                        saving_character.character_strength = bind_from_fragment_characteristics.strengthField.characteristicInputText.text.toString()
                        saving_character.character_dexterity = bind_from_fragment_characteristics.dexterityField.characteristicInputText.text.toString()
                        saving_character.character_constitution = bind_from_fragment_characteristics.constitutionField.characteristicInputText.text.toString()
                        saving_character.character_intelligence = bind_from_fragment_characteristics.intelligenceField.characteristicInputText.text.toString()
                        saving_character.character_wisdom = bind_from_fragment_characteristics.wisdomField.characteristicInputText.text.toString()
                        saving_character.character_charisma = bind_from_fragment_characteristics.charismaField.characteristicInputText.text.toString()

                        saving_character.athletic = bind_from_fragment_characteristics.athleticField.abilityField.isChecked
                        saving_character.acrobatic = bind_from_fragment_characteristics.acrobaticField.abilityField.isChecked
                        saving_character.handDexterity = bind_from_fragment_characteristics.handDexterityField.abilityField.isChecked
                        saving_character.steals = bind_from_fragment_characteristics.stealsField.abilityField .isChecked
                        saving_character.magic = bind_from_fragment_characteristics.magicField.abilityField.isChecked
                        saving_character.history = bind_from_fragment_characteristics.historyField.abilityField.isChecked
                        saving_character.investigation = bind_from_fragment_characteristics.investigationField.abilityField.isChecked
                        saving_character.religion = bind_from_fragment_characteristics.religionField.abilityField.isChecked
                        saving_character.insight = bind_from_fragment_characteristics.insightField.abilityField.isChecked
                        saving_character.medicine = bind_from_fragment_characteristics.medicineField.abilityField.isChecked
                        saving_character.nature = bind_from_fragment_characteristics.natureField.abilityField.isChecked
                        saving_character.perception = bind_from_fragment_characteristics.perceptionField.abilityField.isChecked
                        saving_character.survival = bind_from_fragment_characteristics.survivalField.abilityField.isChecked
                        saving_character.animals = bind_from_fragment_characteristics.animalsField.abilityField.isChecked
                        saving_character.deception = bind_from_fragment_characteristics.deceptionField.abilityField.isChecked
                        saving_character.fear = bind_from_fragment_characteristics.fearField.abilityField.isChecked
                        saving_character.performance = bind_from_fragment_characteristics.performanceField.abilityField.isChecked
                        saving_character.belief = bind_from_fragment_characteristics.beliefField.abilityField.isChecked
                    }
                }
                finish()
                val intent= Intent(this, Character::class.java)
                intent.putExtra("character_id", current_character_id)
                startActivity (intent)
                return true
            }
            else -> { return super.onOptionsItemSelected(item) }
        }
}

class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm!!) {
    fun SectionsPagerAdapter(fm: FragmentManager) {

    }

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> {
                return Characteristics()
            }
            1 -> {
                return Weapon()
            }
            2 -> {
                return Equipment()
            }
            3 -> {
                return Magic()
            }
        }
        return Characteristics()
    }

}}