package cz.uhk.umte.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import cz.uhk.umte.R
import cz.uhk.umte.utils.SensorValue
import cz.uhk.umte.utils.onItemSelected
import cz.uhk.umte.utils.toOurFormat
import kotlinx.android.synthetic.main.activity_sensor.*
import java.text.DecimalFormat

class SensorActivity: AppCompatActivity(), SensorEventListener {

    val df= DecimalFormat("#.##")
    var sensorType = SensorValue.Accelerometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)

        val manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        for(value in SensorValue.values()){
            manager.registerListener(this, manager.getDefaultSensor(value.getType()),SensorManager.SENSOR_DELAY_FASTEST)
        }

        val sensorAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, android.R.id.text1, SensorValue.values())
        spinner.adapter = sensorAdapter

        spinner.onItemSelected<SensorValue>{
            sensorType=it
        }
        /* TODO
        - ukázat si rozdíly senzorů
        - orientation vector
         */
    }

    override fun onAccuracyChanged(sensor: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {

        event?.let{
            if(it.sensor.type == sensorType.getType()){
                xTextView.text = it.values[0].toOurFormat() // X
                yTextView.text = it.values[1].toOurFormat() // Y
                zTextView.text = it.values[2].toOurFormat() // Z
            }
        }
    }
}