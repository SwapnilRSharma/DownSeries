package com.doodlepie.downseries.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.R;

import java.net.URL;

public class EpisodesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView episode_name;
    public String url;

    ItemClickListener itemClickListener;



    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public EpisodesViewHolder(View itemView) {
        super(itemView);
        episode_name = (TextView) itemView.findViewById(R.id.episode_name);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
