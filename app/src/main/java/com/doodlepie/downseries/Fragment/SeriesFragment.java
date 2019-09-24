package com.doodlepie.downseries.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doodlepie.downseries.Common.Common;
import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.R;
import com.doodlepie.downseries.SeasonListActivity;
import com.doodlepie.downseries.ViewHolder.SeriesViewHolder;
import com.doodlepie.downseries.model.seriesItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SeriesFragment extends Fragment {


    //Firebase
    FirebaseDatabase database;
    DatabaseReference seriesList;

    //View
    RecyclerView recyclerView;

    //FirebaseUIAdapter
    FirebaseRecyclerOptions<seriesItem> options;
    FirebaseRecyclerAdapter<seriesItem, SeriesViewHolder> adapter;


    private static SeriesFragment INSTANCE = null;

    public SeriesFragment() {

        database = FirebaseDatabase.getInstance();
        seriesList = database.getReference(Common.STR_SERIES_BACKGROUND);


        options = new FirebaseRecyclerOptions.Builder<seriesItem>()
                .setQuery(seriesList, seriesItem.class) //select all
                .build();

        adapter = new FirebaseRecyclerAdapter<seriesItem, SeriesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final SeriesViewHolder holder, int position, @NonNull final seriesItem model) {

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Loading");
                progressDialog.show();

                Picasso.with(getActivity())
                        .load(model.getBannerLink())
                        .networkPolicy(NetworkPolicy.OFFLINE)
                        .into(holder.background_image,
                               new Callback() {
                            @Override
                            public void onSuccess() {
                                progressDialog.dismiss();
                            }

                                   @Override
                                   public void onError() {
                                        progressDialog.dismiss();
                                       //Toast.makeText(getContext(), "Error occurded while fething data...", Toast.LENGTH_SHORT).show();
                                       Picasso.with(getActivity())
                                               .load(model.getBannerLink())
                                               .error(R.drawable.ic_terrain_black_24dp)
                                               .into(holder.background_image, new Callback() {
                                                   @Override
                                                   public void onSuccess() {

                                                   }

                                                   @Override
                                                   public void onError() {
                                                        progressDialog.dismiss();
                                                       Log.e("ERROR", "Coud'nt fetch image");
                                                   }
                                               });
                                   }


                        });

                holder.series_name.setText(model.getName());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        //Code for Series Detail

                        Common.CATEGORY_ID_SELECTED = adapter.getRef(position).getKey();
                        Common.CATEGORY_SELECTED = model.getName();
                        Intent intent = new Intent(getActivity(), SeasonListActivity.class);
                        startActivity(intent);
                    }

                });

            }

            @Override
            public SeriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_series_item, parent, false);
                return new SeriesViewHolder(itemView);
            }
        };

    }

    public static SeriesFragment getInstance(){
        if(INSTANCE == null)
            INSTANCE = new SeriesFragment();

            return INSTANCE;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_series);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        //setSeries();
        adapter.startListening();
        recyclerView.setAdapter(adapter);

        return view;
    }

    public void setSeries() {

        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onStart() {
        super.onStart();
        if(adapter != null){
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(adapter != null){
            adapter.stopListening();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.startListening();
        }
    }
}





















