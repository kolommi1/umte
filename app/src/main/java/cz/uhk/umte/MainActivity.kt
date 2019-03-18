package cz.uhk.umte

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import cz.uhk.umte.prefs.Prefs
import cz.uhk.umte.ws.ScheduleDTO
import cz.uhk.umte.ws.StagService
import cz.uhk.umte.ws.stagService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        openButton.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("key", "text")
            startActivityForResult(intent, 777)
        }

        openListButton.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

        val appStartMillis = Prefs.getAppStart()
        Toast.makeText(this, "$appStartMillis", Toast.LENGTH_LONG).show()
        Prefs.setAppStart(System.currentTimeMillis())

        wsButton.setOnClickListener {
            stagService.getHarmonogram(StagService.JSON).enqueue(object: Callback<ScheduleDTO>{
                override fun onResponse(call: Call<ScheduleDTO>, response: Response<ScheduleDTO>) {
                   println("onResponse")
                    Toast.makeText(this@MainActivity, response.body()?.toString(),Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<ScheduleDTO>, t: Throwable) {
                    println("onFailure")
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 777 && resultCode == Activity.RESULT_OK) {
            val s = data?.getStringExtra("result")
            //var - proměnná
            //val - value - jen pro čtení
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
        }
    }
}
