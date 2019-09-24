package com.doodlepie.downseries.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.doodlepie.downseries.Fragment.HighQualityFragment;
import com.doodlepie.downseries.Fragment.LowQualityFragment;
import com.doodlepie.downseries.Fragment.MediumQualityFragment;

public class EpisodesFragmentAdapter extends FragmentPagerAdapter{


    private Context context;

    public EpisodesFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            return LowQualityFragment.getInstance();
        }else if (position == 1){
            return MediumQualityFragment.getInstance();
        }else if (position == 2){
            return HighQualityFragment.getInstance();
        }else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "480p";

            case 1:
                return "720p";
            case 2:
                return "1080p";
        }
        return "";
    }
}
