package com.example.babis.bootcamplocator;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements OnMapReadyCallback{


    private GoogleMap mMap;
    private MarkerOptions userMarker;
    private  LocationListFragment listFragment;


    public MainFragment() {
        // Required empty public constructor
    }



    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        listFragment = (LocationListFragment)getActivity().getSupportFragmentManager().findFragmentById(R.id.container_location_lists);
        if(listFragment==null){
            listFragment = LocationListFragment.newInstance();
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_location_lists,listFragment)
                    .commit();
        }



        final EditText zipText = (EditText)view.findViewById(R.id.zip_text);

        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if((event.getAction() == KeyEvent.ACTION_DOWN)&&keyCode == KeyEvent.KEYCODE_ENTER){
                    String text = zipText.getText().toString();
                    int zip = Integer.parseInt(text);

                    //hiding the keyboard
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromInputMethod(zipText.getWindowToken(),0);

                    showList();
                    updateMapForZip(zip);
                }

                return false;
            }
        });


        hideList();
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }


    public void setUserMarker(LatLng latLng){

        if(userMarker==null){
            userMarker = new MarkerOptions().position(latLng).title("Current Location");
            mMap.addMarker(userMarker);
        }


        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation( latLng.latitude,latLng.longitude,1);
            //removing the space in string .replaceAll("\\s+","")
            int zip = Integer.parseInt(addresses.get(0).getPostalCode().replaceAll("\\s+",""));
            updateMapForZip(zip);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getContext(),"zip error",Toast.LENGTH_SHORT).show();
        }

        updateMapForZip(92284);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
    }

    private void hideList(){
        getActivity().getSupportFragmentManager().beginTransaction().hide(listFragment).commit();
    }

    private void showList(){
        getActivity().getSupportFragmentManager().beginTransaction().show(listFragment).commit();
    }



    private void updateMapForZip(int zipcode){

        ArrayList<Devslopes> location = DataService.getInstance().bootcampLocationWithin10Miles(zipcode);


        for(int x=0; x<location.size(); x++){

            Devslopes loc = location.get(x);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc.getLatitude(),loc.getLongitude()));
            marker.title(loc.getLocationTitle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_pin));

            mMap.addMarker(marker);


        }






    }



}
