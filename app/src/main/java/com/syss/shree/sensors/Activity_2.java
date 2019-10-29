package com.syss.shree.sensors;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_open);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new LightFragmentNew()).commit();
        navigationView.setCheckedItem(R.id.nav_light);
            Toast.makeText(this,"Brightness of screen varies according to the light intensity over the light sensor",Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_accel:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new AccelFragment()).commit();
                break;
            case R.id.nav_compass:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new CompassFragment()).commit();
                break;
            case R.id.nav_gyro:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new GyroFragment()).commit();
                break;
            case R.id.nav_light:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new LightFragmentNew()).commit();
                break;
            case R.id.nav_magnet:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new MagnetFragment()).commit();
                break;
            case R.id.nav_prox:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new ProximityFragment()).commit();
                break;
            case R.id.nav_Ref:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cotainer,new RefFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
