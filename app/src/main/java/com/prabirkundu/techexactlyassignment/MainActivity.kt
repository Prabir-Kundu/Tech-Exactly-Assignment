package com.prabirkundu.techexactlyassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.prabirkundu.techexactlyassignment.adapter.ViewPagerAdapter2
import com.prabirkundu.techexactlyassignment.databinding.ActivityMainBinding
import com.prabirkundu.techexactlyassignment.fragment.ApplicationsFragment
import com.prabirkundu.techexactlyassignment.fragment.SettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    private var mTitles: ArrayList<String>? = null
    private var mFragments: ArrayList<Fragment>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //nableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        mTitles = ArrayList()
        mTitles!!.add("DSA")
        mTitles!!.add("Java")
        mTitles!!.add("C++")
        mTitles!!.add("Python")
        mTitles!!.add("Go")
        mTitles!!.add("Swift")
        mTitles!!.add("Kotlin")
        mTitles!!.add("Jetpack Compose")
        mTitles!!.add("R")

        // initializing our array list.
        mFragments = ArrayList()
        mFragments!!.add(ApplicationsFragment())
        mFragments!!.add(SettingFragment())

        val viewPagerAdapter2: ViewPagerAdapter2 = ViewPagerAdapter2(this, mFragments, mTitles)
        binding.mViewPager2.setAdapter(viewPagerAdapter2)
    }
}