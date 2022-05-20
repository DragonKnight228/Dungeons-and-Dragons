package com.example.dungeonsanddragons

import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import io.realm.*
import io.realm.kotlin.where
import java.net.URL
import kotlin.properties.Delegates

class Character : AppCompatActivity() {


    var current_character: DatabaseCharacter? = DatabaseCharacter()
    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration
    var current_character_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val bundle = intent.extras
        if (bundle != null) {
            current_character_id = bundle.getInt("character_id")
        }

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.your_character))
        setSupportActionBar(toolbar)

        Realm.init(this)
        configuration = RealmConfiguration.Builder().name("Characters database").allowWritesOnUiThread(true).build()
        ourRealm = Realm.getInstance(configuration)
        current_character =
            ourRealm.where<DatabaseCharacter>().equalTo("character_id", current_character_id).findFirst()

        val pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        val pager: ViewPager = findViewById(R.id.pager)
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