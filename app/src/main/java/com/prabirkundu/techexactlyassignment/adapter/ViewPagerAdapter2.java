package com.prabirkundu.techexactlyassignment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter2 extends FragmentStateAdapter {
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ArrayList<String> mTitles = new ArrayList<>();

    public ViewPagerAdapter2(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> mFragments, ArrayList<String> mTitles) {
        super(fragmentActivity);
        this.mFragments = mFragments;
        this.mTitles = mTitles;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return mFragments.size();
    }

    public String getPageTitle(int position) {
        return mTitles.get(position);
    }
}
