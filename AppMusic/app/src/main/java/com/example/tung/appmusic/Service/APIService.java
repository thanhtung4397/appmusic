package com.example.tung.appmusic.Service;

import android.provider.ContactsContract;

public class APIService {

    public static  String base_url = "https://thanhtung97.000webhostapp.com/Server/";

    public  static Dataservice getService(){

        return  APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
