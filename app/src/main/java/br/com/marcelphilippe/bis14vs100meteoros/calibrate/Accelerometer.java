package br.com.marcelphilippe.bis14vs100meteoros.calibrate;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import br.com.marcelphilippe.bis14vs100meteoros.config.DeviceSettings;
import br.com.marcelphilippe.bis14vs100meteoros.interfaces.AccelerometerDelegate;

public class Accelerometer implements SensorEventListener {

    private float currentAccelerationX;
    private float currentAccelerationY;
    private AccelerometerDelegate delegate;
    private SensorManager sensorManager;
    static Accelerometer sharedAccelerometer = null;
    private float calibratedAccelerationX;
    private float calibratedAccelerationY;
    private int calibrated;

    public Accelerometer(){
        this.catchAccelerometer();
    }

    public void catchAccelerometer() {
        sensorManager = DeviceSettings.getSensormanager();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent acceleration) {

        if(calibrated < 100){
            this.calibratedAccelerationX += acceleration.values[0];
            this.calibratedAccelerationY += acceleration.values[1];

            System.out.println(acceleration.values[0]);
            System.out.println(acceleration.values[1]);

            calibrated++;

            if (calibrated == 100 ) {
                this.calibratedAccelerationX /= 100;
                this.calibratedAccelerationY /= 100;
            }
            return;
        }

        // Leitura da aceleracao
        this.currentAccelerationX = acceleration.values[0] - this.calibratedAccelerationX;
        this.currentAccelerationY = acceleration.values[1] - this.calibratedAccelerationY;

        /*this.currentAccelerationX = acceleration.values[0];
        this.currentAccelerationY = acceleration.values[1];*/

        // Envia leitura do acelerometro
        if (this.delegate != null) {
            this.delegate.accelerometerDidAccelerate(currentAccelerationX, currentAccelerationY);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public static Accelerometer sharedAccelerometer() {
        if (sharedAccelerometer == null) {
            sharedAccelerometer = new Accelerometer();
        }
        return sharedAccelerometer;
    }

    public void setDelegate(AccelerometerDelegate delegate) {
        this.delegate = delegate;
    }

    public AccelerometerDelegate getDelegate() {
        return delegate;
    }
}
