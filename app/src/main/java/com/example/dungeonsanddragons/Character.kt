package com.example.dungeonsanddragons

import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import java.net.URL

class Character : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)

        val toolbar: android.support.v7.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        var pagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        var pager: ViewPager = findViewById(R.id.pager)
        pager.adapter = pagerAdapter

        var tabLayout: TabLayout = findViewById(R.id.tablayout)
        tabLayout.setupWithViewPager(pager)
        tabLayout.getTabAt(0)?.setIcon(R.drawable.outline_person_24)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.sword)
}
}

class SectionsPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    fun SectionsPagerAdapter(fm: FragmentManager) {

    }

    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment? {
        when (position){
            0 -> {
                return Characteristics()
            }
            1 -> {
                return Equipment()
            }
        }
        return null
    }



}