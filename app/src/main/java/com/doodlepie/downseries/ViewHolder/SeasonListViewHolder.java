package com.doodlepie.downseries.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.R;

public class SeasonListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


    ItemClickListener itemClickListener;
    public ImageView season_image;
    public TextView season_name;
    public Button bAddToFav;

    public SeasonListViewHolder(View itemView) {
        super(itemView);
        season_image = (ImageView)itemView.findViewById(R.id.season_image);
        season_name = (TextView)itemView.findViewById(R.id.season_name);
        bAddToFav = (Button) itemView.findViewById(R.id.bAddToFav);
        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
