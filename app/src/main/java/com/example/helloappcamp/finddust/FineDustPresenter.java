package com.example.helloappcamp.finddust;

import com.example.helloappcamp.data.FineDustRepository;
import com.example.helloappcamp.metrial.Finedust;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FineDustPresenter implements FineDustContract.UserActionListener {



    private final FineDustRepository mRepository;
    private final FineDustContract.View mView;

    public FineDustPresenter(FineDustRepository repository, FineDustContract.View view) {
        this.mRepository = repository;
        this.mView = view;
    }

    @Override
    public void loadFineDustData() {

        if (mRepository.isAvailable()){
            mView.loadingStart();
            mRepository.getFindDustData(new Callback<Finedust>() {
                @Override
                public void onResponse(Call<Finedust> call, Response<Finedust> response) {
                    mView.showFineDustResult(response.body());
                    mView.loadingEnd();
                }

                @Override
                public void onFailure(Call<Finedust> call, Throwable t) {

                    mView.showLongError(t.getLocalizedMessage());
                    mView.loadingEnd();

                }
            });
        }

    }
}
