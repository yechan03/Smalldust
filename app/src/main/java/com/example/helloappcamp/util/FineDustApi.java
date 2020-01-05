package com.example.helloappcamp.util;

import com.example.helloappcamp.metrial.Finedust;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface FineDustApi {
    String BASE_URL = "http://apis.skplantex.com/";

    @Headers("appKey=6b200e091d1a4d7e83fb9b4732809b33")
    @GET("weather/dust?version=1")
    Call<Finedust> getFineDust(@Query("lat") double latitude,
                               @Query("lon") double longitude);
}
