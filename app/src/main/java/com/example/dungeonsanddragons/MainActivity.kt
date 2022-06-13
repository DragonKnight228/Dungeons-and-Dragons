package com.example.dungeonsanddragons

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where


class MainActivity : AppCompatActivity() {


    lateinit var ourRealm: Realm
    lateinit var configuration: RealmConfiguration


    private var our_character: DatabaseCharacter = DatabaseCharacter()
    lateinit var characterList: OrderedRealmCollection<DatabaseCharacter>




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        toolbar.setTitle(this.getString(R.string.storage))
        setSupportActionBar(toolbar)

        Realm.init(this)
        configuration = RealmConfiguration.Builder().name("Characters database").allowWritesOnUiThread(true).build()
        ourRealm = Realm.getInstance(configuration)
        characterList = createCharacterList()

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


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.create_character_button -> {
                val max_id_character: Number? = ourRealm.where<DatabaseCharacter>().max("character_id")
                if (max_id_character != null) {
                    createCharacter((max_id_character.toInt()) + 1,"Pupa","")
                }
                else createCharacter((characterList.size) + 1,"Pupa","")

                goToCharacter(our_character)
                return true}
        else -> { return super.onOptionsItemSelected(item) }
    }
    }

    fun createCharacterList(): OrderedRealmCollection<DatabaseCharacter> {
        return ourRealm.where<DatabaseCharacter>().findAll()
    }

     fun createCharacter(character_id: Int, name: String, level: String): DatabaseCharacter{
        val character = DatabaseCharacter()
         character.character_id = character_id
         character.character_name = name
         character.character_level = level
         ourRealm.executeTransaction {transactionRealm -> transactionRealm.insert(character)}
         our_character = character
        return character
    }

    fun goToCharacter(character: DatabaseCharacter){
        Characteristics.newInstance(character.character_id)


        val intent= Intent(this, Character::class.java)
        intent.putExtra("character_id", character.character_id)
        startActivity (intent)
    }


}






