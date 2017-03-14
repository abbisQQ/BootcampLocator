package com.example.babis.bootcamplocator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Babis on 3/14/2017.
 */

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {


    public ArrayList<Devslopes> locations;

    public LocationsAdapter(ArrayList<Devslopes> locations) {

        this.locations =locations ;

    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_location,parent,false);

        return new LocationsViewHolder(card);
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final Devslopes location =locations.get(position);
        holder.updateUi(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }
}
