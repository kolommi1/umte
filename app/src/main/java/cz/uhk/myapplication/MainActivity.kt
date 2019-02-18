package cz.uhk.myapplication

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        openButton.setOnClickListener {
            val intent = Intent(this,DetailActivity::class.java)
            intent.putExtra("key","text")
            startActivityForResult(intent,777)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 777 && resultCode == Activity.RESULT_OK){
            val s = data?.getStringExtra("result")
            //var - proměnná
            //val - value - jen pro čtení
            Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
        }
    }
}
