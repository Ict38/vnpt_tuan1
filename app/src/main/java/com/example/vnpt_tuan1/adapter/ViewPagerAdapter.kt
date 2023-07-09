package com.example.vnpt_tuan1.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.vnpt_tuan1.fragment.BonusFragment
import com.example.vnpt_tuan1.fragment.FragmentList
import com.example.vnpt_tuan1.fragment.FragmentSearch

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentStatePagerAdapter(fm, behavior){
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FragmentList()
            1 -> return BonusFragment()
            2 -> return FragmentSearch()
        }
        return FragmentList()
    }
}