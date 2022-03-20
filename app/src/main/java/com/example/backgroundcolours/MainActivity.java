package com.example.backgroundcolours;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager sensorManager;
    private View view;
    private long lastUpdate;
    int counterColorChanged =0;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private TextView t4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = findViewById(R.id.bg);
        view.setBackgroundColor(Color.YELLOW);

        this.t1=findViewById(R.id.t1);
        this.t2=findViewById(R.id.t2);
        this.t3=findViewById(R.id.t3);
        this.t4=findViewById(R.id.t4);





        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();

    }
    //overriding two methods of SensorEventListener
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        float accelerationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);

        long actualTime = System.currentTimeMillis();

        if (accelerationSquareRoot >= 2) //it will be executed if you shuffle
        {

            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;//updating lastUpdate for next shuffle

            counterColorChanged++;
            colorChangedMethod1(counterColorChanged);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    void colorChangedMethod1(int colorCounter){
        if(colorCounter == 1) {
            view.setBackgroundColor(getResources().getColor(R.color.black));
            t1.setTextColor(getResources().getColor(R.color.white));
            t2.setTextColor(getResources().getColor(R.color.teal_200));
            t3.setTextColor(getResources().getColor(R.color.white));
            t4.setTextColor(getResources().getColor(R.color.teal_200));

        } else if (colorCounter == 2) {

            view.setBackgroundColor(getResources().getColor(R.color.white));
            t1.setTextColor(getResources().getColor(R.color.black));
            t2.setTextColor(getResources().getColor(R.color.purple_700));
            t3.setTextColor(getResources().getColor(R.color.black));
            t4.setTextColor(getResources().getColor(R.color.purple_700));

        }
        else if (colorCounter == 3) {

            view.setBackgroundColor(getResources().getColor(R.color.purple_500));
            t1.setTextColor(getResources().getColor(R.color.white));
            t2.setTextColor(getResources().getColor(R.color.teal_200));
            t3.setTextColor(getResources().getColor(R.color.white));
            t4.setTextColor(getResources().getColor(R.color.teal_200));


        }else{
            counterColorChanged=0;
            view.setBackgroundColor(getResources().getColor(R.color.teal_700));
            t1.setTextColor(getResources().getColor(R.color.white));
            t2.setTextColor(getResources().getColor(R.color.black));
            t3.setTextColor(getResources().getColor(R.color.white));
            t4.setTextColor(getResources().getColor(R.color.black));


        }



    }
}