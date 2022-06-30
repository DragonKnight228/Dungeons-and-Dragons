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
import com.example.dungeonsanddragons.databinding.ActivityCharacterBinding
import com.example.dungeonsanddragons.databinding.FragmentCharacteristicsBinding
import com.example.dungeonsanddragons.databinding.FragmentEquipmentBinding
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
    lateinit var bind_from_fragment: FragmentCharacteristicsBinding

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
                        saving_character.character_name = bind_from_fragment.fieldName.inputText.text.toString()
                        saving_character.character_level = bind_from_fragment.fieldLevel.inputText.text.toString()
                        saving_character.character_race = bind_from_fragment.fieldRace.inputText.text.toString()
                        saving_character.character_xp = bind_from_fragment.fieldXp.inputText.text.toString()

                        saving_character.character_kd = bind_from_fragment.kdField.inputText1.text.toString()
                        saving_character.character_hits = bind_from_fragment.hpField.inputText1.text.toString()
                        saving_character.character_kh = bind_from_fragment.hpDiceField.inputText1.text.toString()
                        saving_character.character_speed = bind_from_fragment.speedField.inputText1.text.toString()
                        saving_character.character_max_hits = bind_from_fragment.maxHpField.inputText1.text.toString()
                        saving_character.character_initiative = bind_from_fragment.initiativeField.inputText1.text.toString()


                        saving_character.character_strength = bind_from_fragment.strengthField.characteristicInputText.text.toString()
                        saving_character.character_dexterity = bind_from_fragment.dexterityField.characteristicInputText.text.toString()
                        saving_character.character_constitution = bind_from_fragment.constitutionField.characteristicInputText.text.toString()
                        saving_character.character_intelligence = bind_from_fragment.intelligenceField.characteristicInputText.text.toString()
                        saving_character.character_wisdom = bind_from_fragment.wisdomField.characteristicInputText.text.toString()
                        saving_character.character_charisma = bind_from_fragment.charismaField.characteristicInputText.text.toString()
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