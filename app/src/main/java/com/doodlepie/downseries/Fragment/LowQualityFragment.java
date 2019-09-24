package com.doodlepie.downseries.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.doodlepie.downseries.Common.Common;
import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.R;
import com.doodlepie.downseries.ViewHolder.EpisodesViewHolder;
import com.doodlepie.downseries.model.episodeItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class LowQualityFragment extends Fragment {


    Query query;
    //Firebase
    FirebaseDatabase database;
    DatabaseReference LowQualityEpisodes;

    //View
    RecyclerView recyclerView;

    //FirebaseUIAdapter
    FirebaseRecyclerOptions<episodeItem> options;
    FirebaseRecyclerAdapter<episodeItem, EpisodesViewHolder> adapter;


    private static LowQualityFragment INSTANCE = null;


    public LowQualityFragment() {

        database = FirebaseDatabase.getInstance();
        LowQualityEpisodes = database.getReference(Common.STR_LOW_QUALITY_EPISODES);

        query = FirebaseDatabase.getInstance().getReference(Common.STR_LOW_QUALITY_EPISODES)
                .orderByChild("seasonId").equalTo(Common.SEASON_ID_SELECTED);

        options = new FirebaseRecyclerOptions.Builder<episodeItem>()
                .setQuery(query, episodeItem.class)
                .build();

        adapter = new FirebaseRecyclerAdapter<episodeItem, EpisodesViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position, @NonNull final episodeItem model) {

                holder.episode_name.setText(model.getEpisode_name());

                holder.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        String url = model.getUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    }
                });

            }

            @Override
            public EpisodesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layout_episode_item, parent, false);



                return new EpisodesViewHolder(itemView);
            }
        };

    }


    public static LowQualityFragment getInstance(){
        if(INSTANCE == null)
            INSTANCE = new LowQualityFragment();

        return INSTANCE;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_low_quality, container, false);

        recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_low_quality_episodes);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter.startListening();
        recyclerView.setAdapter(adapter);

        return itemView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null){
            adapter.startListening();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adapter!= null){
            adapter.startListening();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (adapter != null){
            adapter.stopListening();
        }
    }


}
