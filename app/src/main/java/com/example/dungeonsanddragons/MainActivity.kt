package com.example.dungeonsanddragons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import io.realm.RealmObject
import java.util.logging.Level

class MyCharacter(): RealmObject{
    class MyCharacterCharacteristics(): RealmObject{
        lateinit var name: String
        lateinit var level: String
        lateinit var race: String
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.storage))
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_character_button -> {
                val intent: Intent = Intent(this, Character::class.java)
                        startActivity (intent)
                return true}
        else -> { return super.onOptionsItemSelected(item) }
    }
    }
}