package com.prabirkundu.techexactlyassignment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val mFragments: MutableList<Fragment>,
    private val mTitles: MutableList<String>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return mFragments[position]
    }

    override fun getItemCount(): Int {
        return mFragments.size
    }

    fun getPageTitle(position: Int): String {
        return mTitles[position]
    }
}
