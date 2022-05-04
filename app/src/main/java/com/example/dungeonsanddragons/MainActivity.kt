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
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.ArrayList
import java.util.logging.Level

class MyCharacter: RealmObject{

    @PrimaryKey var character_id: Int = 0
    class MyCharacterCharacteristics(): RealmObject{
        lateinit var name: String
        lateinit var level: String
        lateinit var race: String
    }
}

data class character(val id: String, val name: String?, val level: String?)

class MainActivity : AppCompatActivity() {


    public val config = RealmConfiguration.Builder(schema = setOf(MyCharacter::class)).build()

    lateinit var current_character: character
    val characterList = createCharacterList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.storage))
        setSupportActionBar(toolbar)

        val myRecycler = findViewById<RecyclerView>(R.id.my_recycler)
        myRecycler.layoutManager = LinearLayoutManager(this)

        val adapter = MyRecyclerAdapter(characterList)
        myRecycler.adapter = adapter

    }

    override fun onResume() {
        super.onResume()

        val myRecycler = findViewById<RecyclerView>(R.id.my_recycler)
        myRecycler.layoutManager = LinearLayoutManager(this)

        val adapter = MyRecyclerAdapter(characterList)
        myRecycler.adapter = adapter

    }

    override fun onPause() {
        super.onPause()
        characterList.add(current_character)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_character_button -> {
                val new_character = createCharacter((characterList.size + 1).toString(),"","")

                val intent= Intent(this, Character::class.java)
                        startActivity (intent)
                return true}
        else -> { return super.onOptionsItemSelected(item) }
    }
    }

    fun createCharacterList(): ArrayList<character>{
        val characterList = ArrayList<character>()
        characterList.add(character("1", "Gazgul Mak Uruk", "10"))
        return characterList
    }

     fun createCharacter(character_id: String, name: String?, level: String?): character{
        val realm = Realm.open(config)
        val character = character(character_id, name, level)
        realm.close()
         current_character = character
        return character
    }

}


