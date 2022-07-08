package com.example.dungeonsanddragons



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection


class WeaponRecyclerAdapterVH(view: View) : RecyclerView.ViewHolder(view) {
    val weapon_name: TextView = view.findViewById(R.id.weapon_name)
    val weapon_damage: TextView = view.findViewById(R.id.damage)
    val weapon_bonus: TextView = view.findViewById(R.id.bonus)
}

class WeaponRecyclerAdapter(private val weaponList: OrderedRealmCollection<CharacterWeapon>) : RealmRecyclerViewAdapter<CharacterWeapon, WeaponRecyclerAdapterVH?>(weaponList, true) {

    lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeaponRecyclerAdapterVH {
        view =
            LayoutInflater.from(parent.context).inflate(R.layout.weapon_field, parent, false)

        return WeaponRecyclerAdapterVH(view)
    }

    override fun onBindViewHolder(holder: WeaponRecyclerAdapterVH, position: Int) {


        val weaponListItems = weaponList[position]
        if (weaponListItems != null) {
            holder.weapon_name.text = weaponListItems.weapon_name
        }
        if (weaponListItems != null) {
            holder.weapon_damage.text = weaponListItems.weapon_damage
        }
        if (weaponListItems != null) {
            holder.weapon_bonus.text = weaponListItems.weapon_bonus
        }
        holder.itemView.setOnClickListener(View.OnClickListener {
            fun onClick(view: View) {}
            val bundle = Bundle()
            weaponListItems?.let { bundle.putString("current_weapon_name", it.weapon_name) }

            val intent = Intent(view.context, New_weapon_activity::class.java)
            if (weaponListItems != null) {
                intent.putExtra("weapon_name", weaponListItems.weapon_name)
            }
            view.context.startActivity(intent)

        })
    }

    override fun getItemCount(): Int {
        return weaponList.size
    }

}
