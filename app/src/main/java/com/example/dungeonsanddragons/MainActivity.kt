package com.example.dungeonsanddragons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.ArrayList
import java.util.logging.Level

class MyCharacter: RealmObject{

    class MyCharacterCharacteristics(): RealmObject{
        @PrimaryKey var character_id: Int = 0
        lateinit var name: String
        lateinit var level: String
        lateinit var race: String
    }
}

data class character(val id: String, val name: String, val level: String)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.storage))
        setSupportActionBar(toolbar)

        val myRecycler = findViewById<RecyclerView>(R.id.my_recycler)
        myRecycler.layoutManager = LinearLayoutManager(this)
        val characterList = ArrayList<character>()
        characterList.add(character("0", "Gazgul Mak Uruk Traka", "10"))
        val adapter = MyRecyclerAdapter(characterList)
        myRecycler.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_character_button -> {
                val intent= Intent(this, Character::class.java)
                        startActivity (intent)
                return true}
        else -> { return super.onOptionsItemSelected(item) }
    }
    }

}


