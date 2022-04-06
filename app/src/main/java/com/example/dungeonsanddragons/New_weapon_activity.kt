package com.example.dungeonsanddragons

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toolbar

class New_weapon_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_weapon)

        val toolbar: android.support.v7.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(R.string.new_weapon)
        setSupportActionBar(toolbar)
    }
}