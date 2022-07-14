package com.example.dungeonsanddragons


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection


class CharacterVH(view: View) : RecyclerView.ViewHolder(view) {
    val textViewId: TextView = view.findViewById(R.id.id_character)
    val textViewName: TextView = view.findViewById(R.id.character_name)
    val textViewLevel: TextView = view.findViewById(R.id.character_level)
}

class MyRecyclerAdapter(private val characterList: OrderedRealmCollection<DatabaseCharacter>) : RealmRecyclerViewAdapter<DatabaseCharacter, CharacterVH?>(characterList, true) {

    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.field_for_character, parent, false)

        return CharacterVH(view)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {


        val characterListItems = characterList[position]
        if (characterListItems != null) {
            holder.textViewId.text = characterListItems.character_id.toString()
        }
        if (characterListItems != null) {
            holder.textViewName.text = characterListItems.character_name
        }
        if (characterListItems != null) {
            holder.textViewLevel.text = characterListItems.character_level
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            fun onClick(view: View){}
            val bundle = Bundle()
            characterListItems?.let { bundle.putInt("current_character_id", it.character_id) }
            val characteristics_fragment = Characteristics()
            characteristics_fragment.arguments = bundle

            val intent= Intent(view.context, Character::class.java)
            if (characterListItems != null) {
                intent.putExtra("character_id", characterListItems.character_id)
            }
            view.context.startActivity(intent)

        })
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


}