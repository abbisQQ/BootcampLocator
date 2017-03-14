package com.example.babis.bootcamplocator;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Babis on 3/14/2017.
 */

public class LocationsViewHolder extends RecyclerView.ViewHolder{


    private ImageView locationImage;
    private TextView locationTitle,locationAddress;


    public LocationsViewHolder(View itemView) {
        super(itemView);

        locationImage = (ImageView)itemView.findViewById(R.id.location_image);
        locationAddress = (TextView)itemView.findViewById(R.id.location_title);
        locationAddress = (TextView)itemView.findViewById(R.id.location_address);


    }


    public void updateUi(Devslopes location){
        String uri = location.getImageURL();
        int resource = locationImage.getResources().getIdentifier(uri,null,locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationAddress.setText(location.getLocationAddress());
    }

}
