package com.doodlepie.downseries.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.doodlepie.downseries.R;

public class MediumQualityFragment extends Fragment {


    private static MediumQualityFragment INSTANCE = null;


    public static MediumQualityFragment getInstance(){
        if(INSTANCE == null)
            INSTANCE = new MediumQualityFragment();

        return INSTANCE;

    }
    public MediumQualityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medium_quality, container, false);
    }


}
