package com.example.finallab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.finallab.Adapter.PagerViewAdapter

class MainActivity : AppCompatActivity() {


    private lateinit var mViewPager: ViewPager
    private lateinit var homeBtn : ImageButton
    private lateinit var searchBtn : ImageButton
    private lateinit var helpBtn : ImageButton
    private lateinit var mPagerAdapter : PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.mViewPager)
        homeBtn = findViewById(R.id.mainScreenBtn)
        searchBtn = findViewById(R.id.searchScreenBtn)
        helpBtn = findViewById(R.id.faqScreenBtn)
        mPagerAdapter = PagerViewAdapter(supportFragmentManager) // how much I hate this. I used some name that was already reserved for some hidden sh**, so I got weird errors, Android in a nutshell
        mViewPager.adapter = mPagerAdapter
        mViewPager.offscreenPageLimit = 3

        mViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeTab(position)
            }

        })
        // this is what is displayed by default
        mViewPager.currentItem = 0
        homeBtn.setImageResource(R.drawable.ic_home_dark_purple)
    }

    private fun changeTab(position: Int) {
        if(position == 0){
            homeBtn.setImageResource(R.drawable.ic_home_dark_purple)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            helpBtn.setImageResource(R.drawable.ic_help_outline_black)
        }
        if(position == 1){
            homeBtn.setImageResource(R.drawable.ic_home_black)
            searchBtn.setImageResource(R.drawable.ic_search_dark_purple)
            helpBtn.setImageResource(R.drawable.ic_help_outline_black)
        }
        if(position == 2){
            homeBtn.setImageResource(R.drawable.ic_home_black)
            searchBtn.setImageResource(R.drawable.ic_search_black)
            helpBtn.setImageResource(R.drawable.ic_help_outline_dark_purple)
        }
    }
}
