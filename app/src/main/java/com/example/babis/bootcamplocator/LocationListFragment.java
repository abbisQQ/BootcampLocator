package com.example.babis.bootcamplocator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LocationListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LocationListFragment extends Fragment {



    public LocationListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static LocationListFragment newInstance() {
        LocationListFragment fragment = new LocationListFragment();

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
        View view =  inflater.inflate(R.layout.fragment_location_list, container, false);


        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recycler_locations);
        recyclerView.setHasFixedSize(true);
        LocationsAdapter adapter = new LocationsAdapter(DataService.getInstance().bootcampLocationWithin10Miles(32200));

        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);




        return view;
    }

}
