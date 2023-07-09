package com.example.vnpt_tuan1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.vnpt_tuan1.R
import com.example.vnpt_tuan1.adapter.ViewPagerAdapter
import com.example.vnpt_tuan1.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            val addIntent = Intent(this@MainActivity, AddActivity::class.java)
            startActivity(addIntent)
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager,
            FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bottomNav.menu.findItem(R.id.item_menu_list).isChecked = true
                    1 -> binding.bottomNav.menu.findItem(R.id.item_menu_bonus).isChecked = true
                    2 -> binding.bottomNav.menu.findItem(R.id.item_menu_search).isChecked = true
                }
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
        binding.bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_menu_list -> binding.viewPager.currentItem = 0
                R.id.item_menu_bonus -> binding.viewPager.currentItem = 1
                R.id.item_menu_search -> binding.viewPager.currentItem = 2
            }
            false
        })
    }
}