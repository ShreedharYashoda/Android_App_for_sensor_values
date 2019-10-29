package com.syss.shree.sensors;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MagnetFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;

    TextView mag_tv;
    Button know_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)

                this.getActivity().getSystemService(Activity.SENSOR_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_magnet_, container, false);
        mag_tv = (TextView) rootView.findViewById(R.id.tv_mag);

        know_btn=(Button) rootView.findViewById(R.id.know_button);

        know_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Magnetometer"));
                startActivity(in);
            }
        });
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float azimuth = Math.round(event.values[0]);
        float pitch = Math.round(event.values[1]);
        float roll = Math.round(event.values[2]);

        double tesla1=Math.sqrt((azimuth*azimuth)+(pitch*pitch)+(roll*roll));
        double tesla=Math.round(tesla1);
        mag_tv.setText(tesla+" µT");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        // First starts (gets called before everything else)
        if (mSensorManager == null) {
            return;
        }

        if (menuVisible) {
            this.registerSensorListener();
        } else {
            this.unregisterSensorListener();
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        if (this.getUserVisibleHint()) {
            this.registerSensorListener();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        this.unregisterSensorListener();
    }

    private void registerSensorListener() {
        mSensorManager.registerListener(this,
                mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}
