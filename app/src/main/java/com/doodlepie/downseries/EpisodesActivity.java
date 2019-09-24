package com.doodlepie.downseries;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.doodlepie.downseries.Adapter.EpisodesFragmentAdapter;

public class EpisodesActivity extends AppCompatActivity {



    TabLayout tabLayoutEpisodes;
    ViewPager viewPagerEpisodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episodes);

        viewPagerEpisodes = (ViewPager) findViewById(R.id.viewPagerEpisodes);
        EpisodesFragmentAdapter episodesFragmentAdapter = new EpisodesFragmentAdapter(getSupportFragmentManager(),this);
        viewPagerEpisodes.setAdapter(episodesFragmentAdapter);

        tabLayoutEpisodes = (TabLayout) findViewById(R.id.tabLayoutEpisodes);
        tabLayoutEpisodes.setupWithViewPager(viewPagerEpisodes);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}


