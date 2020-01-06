package com.example.helloappcamp.finddust;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloappcamp.R;
import com.example.helloappcamp.data.FineDustRepository;
import com.example.helloappcamp.data.LocationFineDustRepository;
import com.example.helloappcamp.metrial.Finedust;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class FineDustFragment extends Fragment implements FineDustContract.View {

    private TextView mLocationTextView;
    private TextView mTimeTextView;
    private TextView mDustTextView;
    private SwipeRefreshLayout mSwifeRefreshLayout;
    private FineDustRepository mRepository;
    private FineDustPresenter mPresenter;

    public static FineDustFragment newInstance(double lat,double lng){
        Bundle args = new Bundle();
        args.putDouble("lat",lat);
        args.putDouble("lng",lng);
        FineDustFragment fragment = new FineDustFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null){
            double lat = getArguments().getDouble("lat");
            double lng = getArguments().getDouble("lng");

            mRepository = new LocationFineDustRepository(lat,lng);
        }else{
            mRepository = new LocationFineDustRepository();
        }
        mPresenter = new FineDustPresenter(mRepository,this);
        mPresenter.loadFineDustData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fine_dust,container,false);

        mLocationTextView = view.findViewById(R.id.result_location_text);
        mTimeTextView = view.findViewById(R.id.result_time_text);
        mDustTextView = view.findViewById(R.id.result_dust_text);
        mSwifeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        if (savedInstanceState!=null){
            //복원
            mLocationTextView.setText(savedInstanceState.getString("location"));
            mTimeTextView.setText(savedInstanceState.getString("time"));
            mDustTextView.setText(savedInstanceState.getString("dust"));
        }

        mSwifeRefreshLayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);

        mSwifeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadFineDustData();
            }
        });



        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("location",mLocationTextView.getText().toString());
        outState.putString("time",mTimeTextView.getText().toString());
        outState.putString("dust",mDustTextView.getText().toString());
    }

    @Override
    public void showFineDustResult(Finedust finedust) {
        mLocationTextView.setText(finedust.getWeather().getDust().get(0).getStation().getName());
        mTimeTextView.setText(finedust.getWeather().getDust().get(0).getTime_Observation());
        mDustTextView.setText(finedust.getWeather().getDust().get(0).getPm10().getValue()+" μg/m3,"+finedust.getWeather().getDust().get(0).getPm10().getGrade());
    }

    @Override
    public void showLongError(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loadingStart() {
        mSwifeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadingEnd() {
        mSwifeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void reload(double lat, double lng) {

        mRepository = new LocationFineDustRepository(lat,lng);
        mPresenter = new FineDustPresenter(mRepository,this);
        mPresenter.loadFineDustData();

    }
}
