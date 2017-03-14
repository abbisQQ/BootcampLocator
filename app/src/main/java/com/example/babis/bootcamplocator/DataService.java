package com.example.babis.bootcamplocator;

import java.util.ArrayList;

/**
 * Created by Babis on 3/14/2017.
 */

class DataService {
    private static final DataService instance = new DataService();

    static DataService getInstance() {
        return instance;
    }

    private DataService() {
    }



    public ArrayList<Devslopes> bootcampLocationWithin10Miles(int zipcode){

        ArrayList<Devslopes> list = new ArrayList<>();
        list.add(new Devslopes(35.279f,-120.663f,"Downtown","762 Higuera Street, San Luis Obispo, CA 93401","slo"));
        list.add(new Devslopes(35.302f,-120.658f,"On The Campus","1 Grand Ave, San Luis Obispo, CA 93401","slo"));
        list.add(new Devslopes(35.267f,-120.652f,"East Side Tower","2494 Victoria Ave, San Luis Obispo, CA 93401","slo"));
        return list;

    }

}
