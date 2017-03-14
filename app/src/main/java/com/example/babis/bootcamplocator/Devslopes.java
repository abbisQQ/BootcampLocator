package com.example.babis.bootcamplocator;

/**
 * Created by Babis on 3/14/2017.
 */

public class Devslopes {

    final String DRAWABLE = "drawable/";


    private float longitude;
    private float latitude;
    private String locationTitle,locationAddress,imageUrl;


    public Devslopes(float latitude,float longitude, String locationTitle, String locationAddress, String imageUrl) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.imageUrl = imageUrl;

    }

    public String getImageURL(){
        return  DRAWABLE+imageUrl;
    }




    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
