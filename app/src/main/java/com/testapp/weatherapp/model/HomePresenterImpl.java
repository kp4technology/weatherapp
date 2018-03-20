package com.testapp.weatherapp.model;

import com.testapp.weatherapp.presenter.HomePresenter;
import com.testapp.weatherapp.view.HomeView;

public class HomePresenterImpl implements HomePresenter {

    private HomeView homeView;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
    }

    @Override
    public void searchLocation(String query) {
        Boolean isLocationAvailable = true;
        if (isLocationAvailable)
            homeView.onLocationReceived(query, 0.0, 0.0);
        else
            homeView.onLocationError("Location Not Found");
    }
}
