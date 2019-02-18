package cz.uhk.myapplication

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailTextView.text = "Hello World"

        val sent = intent.getStringExtra("key")
        Toast.makeText(this, sent, Toast.LENGTH_SHORT).show()

        confirmButton.setOnClickListener {
            var builder = AlertDialog.Builder(this)
            builder.setTitle("Potvrdit?")
            builder.setMessage("Opravdu chcete aktivitu ukončit?")
            builder.setPositiveButton("Ano"){dialog, _ ->
                val resultIntent = Intent()
                resultIntent.putExtra("result", "Done!")
                setResult(Activity.RESULT_OK,resultIntent)
                dialog.dismiss()
                finish()
            }

            builder.setNegativeButton("Zrušit"){dialog, _ ->
                dialog.dismiss()
            }

            builder.create().show()


        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
