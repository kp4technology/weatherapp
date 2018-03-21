package com.testapp.weatherapp.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.testapp.weatherapp.R;
import com.testapp.weatherapp.model.HomePresenterImpl;
import com.testapp.weatherapp.presenter.HomePresenter;
import com.testapp.weatherapp.utils.PermissionHelper;
import com.testapp.weatherapp.view.HomeView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, HomeView, LocationListener {

    TextView tvLoction;
    LinearLayout llActionBar;
    HomePresenter homePresenter;
    LocationManager locationManager;
    String provider;
    static double lat, lng;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvLoction = (TextView) findViewById(R.id.tv_location);
        llActionBar = (LinearLayout) findViewById(R.id.ll_actionbar);

        llActionBar.setOnClickListener(this);

        homePresenter = new HomePresenterImpl(this);

        //Get Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        provider = locationManager.getBestProvider(new Criteria(), false);
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PermissionHelper.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    boolean result = PermissionHelper.checkPermissionCamera(this);
                    if (result) {
                        location = locationManager.getLastKnownLocation(provider);
                    }
                } else {
                    Snackbar.make(findViewById(R.id.rootView), "Storage permission denied.", Snackbar.LENGTH_SHORT);
                }
                break;
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionHelper.checkPermissionLocation(this))
            location = locationManager.getLastKnownLocation(provider);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (PermissionHelper.checkPermissionLocation(this))
            locationManager.removeUpdates(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_actionbar:
                homePresenter.searchLocation(tvLoction.getText().toString().trim());
                break;
        }

    }

    @Override
    public void onLocationReceived(String location, Double lat, Double lon) {
        tvLoction.setText(location);
    }

    @Override
    public void onLocationError(String error) {
        tvLoction.setText(error);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
