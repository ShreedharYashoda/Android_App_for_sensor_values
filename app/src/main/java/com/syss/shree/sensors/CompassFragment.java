package com.syss.shree.sensors;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class CompassFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;

    ImageView arrow;
    TextView degrees;


    private float currentDegree = 0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSensorManager = (SensorManager)

                this.getActivity().getSystemService(Activity.SENSOR_SERVICE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_compass, container, false);
        arrow=(ImageView)rootView.findViewById(R.id.imageView5);
        degrees=(TextView)rootView.findViewById(R.id.textView);

        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
            int degree = Math.round(event.values[0]);

            degrees.setText(Integer.toString(degree) + (char) 0x00B0);

            RotateAnimation ra = new RotateAnimation(currentDegree,-degree,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            ra.setDuration(1000);
            ra.setFillAfter(true);

            arrow.startAnimation(ra);
            currentDegree=-degree;
        }



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
                mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);


    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);

    }
}