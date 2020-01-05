package com.example.helloappcamp.data;

import com.example.helloappcamp.metrial.Finedust;

import retrofit2.Callback;

public interface FineDustRepository {
    boolean isAvailable();
    void getFindDustData(Callback<Finedust> callback);
}
