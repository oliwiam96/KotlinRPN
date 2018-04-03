package com.oliwia.reversepolishnot

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*
import android.widget.RadioButton


class SettingsActivity : AppCompatActivity() {

    var colorInt:Int = android.graphics.Color.rgb(255, 255, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val extras = intent.extras ?: return
        val message = extras.getString("Parametr")
        statusText.text = message
        white.isChecked = true

    }

    override fun finish() {
        val data = Intent()
        data.putExtra("colorInt", colorInt.toString())
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }

    fun onRadioButtonClicked(view: View) {
        // Is the button now checked?
        val checked = (view as RadioButton).isChecked

        // Check which radio button was clicked
        when (view.getId()) {
            R.id.blue -> {
                if (checked) {
                    // Pirates are the best
                    statusText.text = "Blue"
                    colorInt = android.graphics.Color.rgb(167, 255, 235)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.gray -> {
                if (checked) {
                    statusText.text = "Gray"
                    colorInt = android.graphics.Color.LTGRAY
                    view.rootView.setBackgroundColor(colorInt)
                }


            }
            R.id.orange -> {
                if (checked) {
                    statusText.text = "Orange"
                    colorInt = android.graphics.Color.rgb(253, 214, 170)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.green -> {
                if (checked) {
                    statusText.text = "Green"
                    colorInt = android.graphics.Color.rgb(190, 241, 139)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.white -> {
                if (checked) {
                    statusText.text = "White"
                    colorInt = android.graphics.Color.rgb(255, 255, 255)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
        }
    }
}
