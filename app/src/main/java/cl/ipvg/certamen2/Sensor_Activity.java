package cl.ipvg.certamen2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class Sensor_Activity extends AppCompatActivity {

    TextView textX,textY,textZ;
    SensorManager sensorManager;
    Sensor sensor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        textX=findViewById(R.id.tvX);
        textY=findViewById(R.id.tvY);
        textZ=findViewById(R.id.tvZ);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

    }
    public void onResume(){
        super.onResume();

        sensorManager.registerListener(gyroListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void onStop(){
        super.onStop();
        sensorManager.unregisterListener(gyroListener);
    }

    public SensorEventListener gyroListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            textX.setText("x:"+ (int)x);
            textY.setText("y:"+ (int)y);
            textZ.setText("z:"+ (int)z);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}