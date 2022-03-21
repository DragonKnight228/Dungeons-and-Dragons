package com.example.dungeonsanddragons

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


class Characteristics : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val field_name = view?.findViewById<View>(R.id.linearLayout)
        if (field_name != null) {
            field_name.setBackgroundResource(R.drawable.shape_for_field)
        }
        return inflater.inflate(R.layout.fragment_characteristics, container, false)
    }


}