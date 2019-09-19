package com.example.tubes_pbp_kelompok3;

public class UtilsApi {
    public static final String BASE_URL_API = "https://tubespbpkelompok3.firebaseio.com";

    public static ApiInterface getAPIService(){ //uji coba, didalam parameter pertama diisi base url
        return ApiClient.getClient().create(ApiInterface.class);
    }
}
