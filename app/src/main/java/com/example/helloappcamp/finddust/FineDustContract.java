package com.example.helloappcamp.finddust;

import com.example.helloappcamp.metrial.Finedust;

public class FineDustContract {
    interface View{
        void showFineDustResult(Finedust finedust);
        void showLongError(String message);
        void loadingStart();
        void loadingEnd();
        void reload(double lat,double lng);

    }
    interface UserActionListener{
        void loadFineDustData();
    }

}
