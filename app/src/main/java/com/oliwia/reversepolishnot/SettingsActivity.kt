package com.oliwia.reversepolishnot

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*


class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val extras = intent.extras ?: return
        val message = extras.getString("Parametr")
        statusText.text = message
    }

    override fun finish(){
        val data = Intent()

        data.putExtra("returnString1", "Wiadomość dla Ciebie")
        setResult(Activity.RESULT_OK, data)

        super.finish()
    }
}
