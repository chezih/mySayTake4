package com.chamud.cheziandsima.mysaytake4.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chamud.cheziandsima.mysaytake4.Fragments.BillsFragment;
import com.chamud.cheziandsima.mysaytake4.R;

/**
 * Created by CHoyzer on 06/09/2015.
 */
public class TabsPageAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String tabTitles[] = new String[2];

    public TabsPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        tabTitles[0] = mContext.getResources().getString(R.string.private_bills);
        tabTitles[1] = mContext.getResources().getString(R.string.bills);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                // Top Rated fragment activity
                return new BillsFragment();
            case 1:
                // Games fragment activity
                return new BillsFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Return the tab title to SlidingTabLayout
        return tabTitles[position];
    }
}
