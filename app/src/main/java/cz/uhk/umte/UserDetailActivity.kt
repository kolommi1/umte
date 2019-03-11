package cz.uhk.umte

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import cz.uhk.umte.model.UserDB
import kotlinx.android.synthetic.main.activity_user_detail.*



class UserDetailActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val index = intent.getIntExtra("index",0)
        val user = intent.extras?.getSerializable("user") as UserDB

        nameEditText.setText(user.name)
        lastNameEditText.setText(user.lastName)
        ageEditText.setText(user.age.toString())
        weightEditText.setText(user.weight.toString())

        saveButton.setOnClickListener {
            val intent = Intent()
            user.name = nameEditText.text.toString()
            user.lastName = lastNameEditText.text.toString()
            user.age = ageEditText.text.toString().toInt()
            user.weight = weightEditText.text.toString().toFloat()

            intent.putExtra("user",  user)
            intent.putExtra("index",  index)
            setResult(Activity.RESULT_OK,intent)
            finish()
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}