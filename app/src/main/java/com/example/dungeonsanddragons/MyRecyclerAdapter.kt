package com.example.dungeonsanddragons


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.RealmResults


class CharacterVH(view: View) : RecyclerView.ViewHolder(view) {
    val textViewId: TextView = view.findViewById(R.id.id_character)
    val textViewName: TextView = view.findViewById(R.id.character_name)
    val textViewLevel: TextView = view.findViewById(R.id.character_level)
}

class MyRecyclerAdapter(private val characterList: OrderedRealmCollection<DatabaseCharacter?>) : RealmRecyclerViewAdapter<DatabaseCharacter?, CharacterVH?>(characterList, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.field_for_character, parent, false)
        return CharacterVH(view)
    }

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        val characterListItems = characterList[position]
        if (characterListItems != null) {
            holder.textViewId.text = characterListItems.character_id.toString()
        }
        holder.textViewName.text = characterListItems.name
        holder.textViewLevel.text = characterListItems.level
    }

    override fun getItemCount(): Int {
        return characterList.size
    }


}