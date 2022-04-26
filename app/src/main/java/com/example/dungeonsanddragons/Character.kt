package com.example.dungeonsanddragons

import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import java.net.URL

class Character : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.your_character))
        setSupportActionBar(toolbar)

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



}