package com.oliwia.reversepolishnot

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*
import android.widget.RadioButton
import android.widget.NumberPicker




class SettingsActivity : AppCompatActivity() {

    var colorInt:Int = android.graphics.Color.rgb(255, 255, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        val extras = intent.extras ?: return
        val message = extras.getString("Parametr")
        white.isChecked = true
        //Set the minimum value of NumberPicker
        numberPicker.minValue = 1
        //Specify the maximum value/number of NumberPicker
        numberPicker.maxValue = 7
        //Gets whether the selector wheel wraps when reaching the min/max value.
        numberPicker.wrapSelectorWheel = true
        numberPicker.value = 4


    }

    override fun finish() {
        val data = Intent()
        data.putExtra("colorInt", colorInt.toString())
        data.putExtra("precision", numberPicker.value.toString())
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
                    colorInt = android.graphics.Color.rgb(167, 255, 235)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.gray -> {
                if (checked) {
                    colorInt = android.graphics.Color.LTGRAY
                    view.rootView.setBackgroundColor(colorInt)
                }


            }
            R.id.orange -> {
                if (checked) {
                    colorInt = android.graphics.Color.rgb(253, 214, 170)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.green -> {
                if (checked) {
                    colorInt = android.graphics.Color.rgb(190, 241, 139)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
            R.id.white -> {
                if (checked) {
                    colorInt = android.graphics.Color.rgb(255, 255, 255)
                    view.rootView.setBackgroundColor(colorInt)
                }

            }
        }
    }
}
