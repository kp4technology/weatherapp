package com.testapp.weatherapp.activities;

import android.os.Bundle;
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
import com.testapp.weatherapp.view.HomeView;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, HomeView {

    TextView tvLoction;
    LinearLayout llActionBar;
    HomePresenter homePresenter;

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
}
