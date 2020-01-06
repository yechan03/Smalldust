package com.example.helloappcamp.metrial;

public class Dust {
    private Station station;
    private String time_Observation;
    private Pm10 pm10;

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getTime_Observation() {
        return time_Observation;
    }

    public void setTime_Observation(String time_Observation) {
        this.time_Observation = time_Observation;
    }

    public Pm10 getPm10() {
        return pm10;
    }

    public void setPm10(Pm10 pm10) {
        this.pm10 = pm10;
    }
}
