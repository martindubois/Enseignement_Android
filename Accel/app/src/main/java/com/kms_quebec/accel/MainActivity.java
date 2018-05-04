package com.kms_quebec.accel;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    @Override
    public void onSensorChanged(SensorEvent event) {
        mTextViewX.setText(String.valueOf(event.values[0]));
        mTextViewY.setText(String.valueOf(event.values[1]));
        mTextViewZ.setText(String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)(getSystemService( SENSOR_SERVICE ));

        mAccelerometer = mSensorManager.getDefaultSensor( Sensor.TYPE_ACCELEROMETER );

        mTextViewX = (TextView)(findViewById(R.id.textViewX));
        mTextViewY = (TextView)(findViewById(R.id.textViewY));
        mTextViewZ = (TextView)(findViewById(R.id.textViewZ));
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, 1000);
    }

    private Sensor        mAccelerometer;
    private SensorManager mSensorManager;

    private TextView mTextViewX;
    private TextView mTextViewY;
    private TextView mTextViewZ;

}
