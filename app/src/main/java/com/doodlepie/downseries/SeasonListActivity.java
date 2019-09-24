package com.doodlepie.downseries;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.doodlepie.downseries.Common.Common;
import com.doodlepie.downseries.Database.Database;
import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.ViewHolder.SeasonListViewHolder;
import com.doodlepie.downseries.model.seasonItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class SeasonListActivity extends AppCompatActivity {

    Query query;
    FirebaseRecyclerOptions<seasonItem> options;
    FirebaseRecyclerAdapter<seasonItem, SeasonListViewHolder> adapter;

    RecyclerView recyclerView;



    //Favorites
    Button bAddToFav;
    Database localDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_list);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(Common.CATEGORY_SELECTED);

        localDB = new Database(this);

//      setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bAddToFav = (Button)findViewById(R.id.bAddToFav);
        /*
        bAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(bAddToFav, "Added to Favorites", Snackbar.LENGTH_LONG).show();
            }
        });
         */
        recyclerView = (RecyclerView) findViewById(R.id.recycler_season_list);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        //Add Favorites
        if (localDB.isFavorite(Common.CATEGORY_ID_SELECTED))
            bAddToFav.setBackgroundColor(Color.GREEN);

        //Click to change state of Favorites
        bAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!localDB.isFavorite(Common.CATEGORY_ID_SELECTED)) {

                    //localDB.open();
                    localDB.addToFavorites(Common.CATEGORY_ID_SELECTED);
                    //localDB.close();
                    bAddToFav.setBackgroundColor(Color.GREEN);
                    Snackbar.make(bAddToFav, "" + Common.CATEGORY_SELECTED + "was added to Favorites", Snackbar.LENGTH_LONG).show();

                }else {
                    //localDB.open();
                    localDB.removeFromFavorites(Common.CATEGORY_ID_SELECTED);
                    //localDB.close();
                    bAddToFav.setBackgroundColor(Color.BLACK);
                    Snackbar.make(bAddToFav, "" + Common.CATEGORY_SELECTED + "was removed from Favorites", Snackbar.LENGTH_LONG).show();
                }
            }

        });

        loadBackgroundList();
    }

    private void loadBackgroundList() {

        query = FirebaseDatabase.getInstance().getReference(Common.STR_SEASON)
                .orderByChild("categoryId").equalTo(Common.CATEGORY_ID_SELECTED);

        options = new FirebaseRecyclerOptions.Builder<seasonItem>()
                .setQuery(query, seasonItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<seasonItem, SeasonListViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final SeasonListViewHolder holder, final int position, @NonNull final seasonItem model) {





               Picasso.with(getBaseContext())
                        .load(model.getSeason_image())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.season_image, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Picasso.with(getBaseContext())
                                        .load(model.getSeason_image())
                                        .into(holder.season_image);

                            }
                        });

                holder.season_name.setText(model.getSeason_name());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Common.SEASON_ID_SELECTED = adapter.getRef(position).getKey();
                        Common.SEASON_SELECTED = model.getSeason_name();
                        Intent intent = new Intent(SeasonListActivity.this, EpisodesActivity.class);
                        startActivity(intent);
                    }
                });



            }

            @Override
            public SeasonListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_season_item, parent,false);
                int height = parent.getMeasuredHeight()/2;
                itemView.setMinimumHeight(height);
                return new SeasonListViewHolder(itemView);
            }
        };

        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null)
            adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            adapter.startListening();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return true;
    }
}
