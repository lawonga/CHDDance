package com.dance.chd.chddance.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.dance.chd.chddance.view.fragment.ExoticDancerList;

/**
 * Created by Andy on 11/6/2016.
 */

public class ManWomanPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2; // There are two items in our page. Active and history.
    public static final int MAN = 1, WOMAN = 0; // KeyFragment locations
    SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public ManWomanPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    /**
     * Item is determinant on these things: REQUESTS, ORDERS
     * They can be WOMAN or HISTORY tabs
     *
     * @param position pager position
     * @return our Fragment
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case WOMAN:
                return ExoticDancerList.newInstance(true);
            case MAN:
                return ExoticDancerList.newInstance(false);
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case WOMAN:
                return "Woman";
            case MAN:
                return "Man";
        }
        return "ERROR";
    }

    public SparseArray<Fragment> getRegisteredFragments() {
        return registeredFragments;
    }
}
