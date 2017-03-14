 package com.example.babis.bootcamplocator;

import android.*;
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;


 public class MapsActivity extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener,GoogleApiClient.ConnectionCallbacks,LocationListener{


    private GoogleApiClient mGoogleApiClient;
     final int PERMISSION_LOCATION = 111;
     private  MainFragment mainFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mainFragment = (MainFragment)getSupportFragmentManager().findFragmentById(R.id.container_main);


        mGoogleApiClient =  new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();



        if(mainFragment == null){
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main,mainFragment)
                    .commit();
        }




    }


     @Override
     public void onConnected(@Nullable Bundle bundle) {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                //request permissions
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_LOCATION);
        }else {
            startLocationServices();
        }


     }

     @Override
     public void onConnectionSuspended(int i) {

     }

     @Override
     public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

     }

     @Override
     public void onLocationChanged(Location location) {

         mainFragment.setUserMarker(new LatLng(location.getLatitude(),(location.getLongitude())));

     }


     @Override
     protected void onStart() {
         mGoogleApiClient.connect();
         super.onStart();
     }

     @Override
     protected void onStop() {
         mGoogleApiClient.disconnect();
         super.onStop();
     }

     public void startLocationServices(){
            //least amount of power and low accuracy
         LocationRequest req = LocationRequest.create().setPriority(LocationRequest.PRIORITY_LOW_POWER);
        try {


            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, req, this);
        }catch (SecurityException e){
            Toast.makeText(this,"No permission",Toast.LENGTH_SHORT).show();
        }


     }

     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);

         switch (requestCode){
             case PERMISSION_LOCATION:
                 if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                     startLocationServices();
                 }else {
                     Toast.makeText(this,"No permission",Toast.LENGTH_SHORT).show();
                 }
         }


     }
 }
