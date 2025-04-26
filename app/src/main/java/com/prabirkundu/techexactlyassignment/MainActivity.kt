package com.prabirkundu.techexactlyassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.prabirkundu.techexactlyassignment.Repository.Repository
import com.prabirkundu.techexactlyassignment.ViewModel.MainViewModel
import com.prabirkundu.techexactlyassignment.ViewModel.MainViewModelProviderFactory
import com.prabirkundu.techexactlyassignment.adapter.ViewPagerAdapter
import com.prabirkundu.techexactlyassignment.databinding.ActivityMainBinding
import com.prabirkundu.techexactlyassignment.fragment.ApplicationsFragment
import com.prabirkundu.techexactlyassignment.fragment.SettingFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    private var mTitles:MutableList<String> =  mutableListOf<String>()
    private var mFragments:MutableList<Fragment> = mutableListOf<Fragment>()
    lateinit var viewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        mTitles!!.add("Applications")
        mTitles!!.add("Settings")

        // initializing our array list.

        mFragments!!.add(ApplicationsFragment())
        mFragments!!.add(SettingFragment())

        val viewPagerAdapter2: ViewPagerAdapter = ViewPagerAdapter(this, mFragments, mTitles)
        binding.mViewPager2.setAdapter(viewPagerAdapter2)

        val repository = Repository()
        val mainViewModelProviderFactory = MainViewModelProviderFactory(repository);
        viewModel = ViewModelProvider(this,mainViewModelProviderFactory).get(MainViewModel::class.java)

        //tabLayout.setupWithViewPager(mViewPager);
        TabLayoutMediator(
            binding.tabLayout, binding.mViewPager2
        ) { tab: TabLayout.Tab, position: Int ->
            tab.setText(viewPagerAdapter2.getPageTitle(position)) // Set the title for each tab
        }.attach()
    }
}