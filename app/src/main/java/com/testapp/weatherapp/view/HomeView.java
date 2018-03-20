package com.testapp.weatherapp.view;

public interface HomeView {
    void onLocationReceived(String location, Double lat, Double lon);

    void onLocationError(String error);
}
