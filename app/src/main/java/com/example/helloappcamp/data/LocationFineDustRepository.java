package com.example.helloappcamp.data;

import com.example.helloappcamp.metrial.Finedust;
import com.example.helloappcamp.util.FineDustApi;
import com.example.helloappcamp.util.FineDustUtil;

import retrofit2.Callback;

public class LocationFineDustRepository implements FineDustRepository {
    private FineDustUtil mFineDustUtil;
    private double mLatitude;
    private double mLongitude;



    public LocationFineDustRepository(){

        mFineDustUtil = new FineDustUtil();

    }

    public LocationFineDustRepository(double lat, double lng) {
        this.mLatitude = lat;
        this.mLongitude = lng;
    }


    @Override
    public boolean isAvailable() {
        if(mLatitude !=0.0 && mLongitude != 0.0){
            return true;
        }
        return false;
    }

    @Override
    public void getFindDustData(Callback<Finedust> callback) {

        mFineDustUtil.getApi().getFineDust(mLatitude,mLongitude).enqueue(callback);

    }
}
