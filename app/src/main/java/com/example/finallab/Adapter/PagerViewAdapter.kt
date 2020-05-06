package com.example.finallab.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.finallab.Adapter.Fragments.HelpFragment
import com.example.finallab.Adapter.Fragments.HomeFragment
import com.example.finallab.Adapter.Fragments.SearchFragment


         class PagerViewAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
            override fun getItem(position: Int): Fragment? {

        return when(position){
            0 -> { HomeFragment()}
            1 -> { SearchFragment()}
            2 -> { HelpFragment()}
            else -> HomeFragment()
        }
    }

    override fun getCount(): Int {
        return 3;
    }

}