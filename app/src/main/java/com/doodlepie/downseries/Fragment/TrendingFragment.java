package com.doodlepie.downseries.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doodlepie.downseries.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingFragment extends Fragment {


    private static TrendingFragment INSTANCE = null;



    public TrendingFragment() {
        // Required empty public constructor
    }

    public static TrendingFragment getInstance(){
        if(INSTANCE == null)
            INSTANCE = new TrendingFragment();

            return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

}
