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

public class AccelFragment extends Fragment implements SensorEventListener {
    private SensorManager mSensorManager;

    TextView tv_x,tv_y,tv_z;
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
        View rootView = inflater.inflate(R.layout.activity_accel_, container, false);
        tv_x = (TextView) rootView.findViewById(R.id.tv_ax);
        tv_y = (TextView) rootView.findViewById(R.id.tv_ay);
        tv_z = (TextView) rootView.findViewById(R.id.tv_az);
        know_btn=(Button) rootView.findViewById(R.id.know_button);

        know_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dimensionengineering.com/info/accelerometers"));
                startActivity(in);
            }
        });
        return rootView;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        tv_x.setText("X:" + event.values[0]);
        tv_y.setText("y:" + event.values[1]);
        tv_z.setText("Z:" + event.values[2]);

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
                mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void unregisterSensorListener() {
        mSensorManager.unregisterListener(this);
    }
}