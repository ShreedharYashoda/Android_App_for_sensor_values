package com.syss.shree.sensors;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class LightFragmentNew extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;

    TextView tv_light;

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
        View rootView = inflater.inflate(R.layout.fragment_light_fragment_new, container, false);
        tv_light = (TextView) rootView.findViewById(R.id.textView3);
        know_btn=(Button) rootView.findViewById(R.id.know_button);

        know_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.quora.com/How-does-the-light-sensor-works"));
                startActivity(in);
            }
        });

        Toast.makeText(getContext(),"Brightness of screen varies according to the light intensity over the light sensor",Toast.LENGTH_LONG).show();
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        double x= Math.round(event.values[0]);
        tv_light.setText("" +x +" Lx");

        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.screenBrightness =event.values[0];// i needed to dim the display
        getActivity().getWindow().setAttributes(lp);


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
                mSensorManager.getSensorList(Sensor.TYPE_LIGHT).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}