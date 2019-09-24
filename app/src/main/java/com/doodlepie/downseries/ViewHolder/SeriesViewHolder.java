package com.doodlepie.downseries.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doodlepie.downseries.Interface.ItemClickListener;
import com.doodlepie.downseries.R;

public class SeriesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView series_name;
    public ImageView background_image;

    ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public SeriesViewHolder(View itemView) {
        super(itemView);
        series_name = (TextView) itemView.findViewById(R.id.name);
        background_image = (ImageView) itemView.findViewById(R.id.background_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());

    }
}
