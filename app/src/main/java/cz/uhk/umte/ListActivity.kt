package cz.uhk.umte

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ListFragment())
                .commitAllowingStateLoss()
        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
