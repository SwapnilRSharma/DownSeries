package com.doodlepie.downseries.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doodlepie.downseries.Fragment.FavoritesFragment;
import com.doodlepie.downseries.Fragment.SeriesFragment;
import com.doodlepie.downseries.Fragment.TrendingFragment;

public class MyFragmentAdapter extends FragmentPagerAdapter {


    private Context context;

    public MyFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SeriesFragment.getInstance();
        } else if (position == 1) {
            return TrendingFragment.getInstance();
        }
        /*else if (position == 2) {
            return FavoritesFragment.getInstance();
        }
        */
        else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Series";

            case 1:
                return "Trending";
/*
            case 2:
                return "Favorites";
                */

        }
        return "";
    }
}
