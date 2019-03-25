package cz.uhk.umte.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cz.uhk.umte.R

class SensorActivity: AppCompatActivity(), SensorEventListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        manager.registerListener(
            this,
            manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_FASTEST)

        /* TODO
        - práva a definice aktivity v manifestu
        - plnit xyzTextView hodnotami (formátovat)
        - ukázat si rozdíly senzorů
        - orientation vector
         */
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {

    }
}