package com.example.helloappcamp.data;

import com.example.helloappcamp.metrial.Finedust;
import com.example.helloappcamp.util.FineDustApi;
import com.example.helloappcamp.util.FineDustUtil;

import retrofit2.Callback;

public class LocationFindDustRepository implements FineDustRepository {
    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;



    public LocationFindDustRepository(){
        mFineDustUtil = new FineDustUtil();
    }
    public LocationFindDustRepository(double mLatitude, double mLongitude) {
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
    }


    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void getFindDustData(Callback<Finedust> callback) {

    }
}
