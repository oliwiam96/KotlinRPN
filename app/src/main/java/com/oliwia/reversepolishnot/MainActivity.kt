package com.oliwia.reversepolishnot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    val stack:Stack = Stack()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState!= null){
            stackText.text =savedInstanceState.getString("param") ?: ""
            stackText.text = "0" // UWAGA!  ODKOMENTOWAĆ
        } else{
            stackText.text = "0"
        }


    }

        fun click(v: View){
        //stackText.text = "Klik!"
        var newString = ""
        when(v.id){
            R.id.button0 -> newString = stackText.text.toString() + "0"
            R.id.button1 -> newString = stackText.text.toString() + "1"
            R.id.button2 -> newString = stackText.text.toString() + "2"
            R.id.button3 -> newString = stackText.text.toString() + "3"
            R.id.button4 -> newString = stackText.text.toString() + "4"
            R.id.button5 -> newString = stackText.text.toString() + "5"
            R.id.button6 -> newString = stackText.text.toString() + "6"
            R.id.button7 -> newString = stackText.text.toString() + "7"
            R.id.button8 -> newString = stackText.text.toString() + "8"
            R.id.button9 -> newString = stackText.text.toString() + "9"
            R.id.buttonDOT -> newString = stackText.text.toString() + "."
            R.id.buttonC -> newString = stackText.text.toString().substring(0, stackText.text.toString().length - 1)
        }
        try{
            var number = newString.toDouble()
            if(newString.length > 1 && newString[0] == '0' && newString[1] != '.'){
                newString = newString.substring(1, newString.length)
            }
            stackText.text = newString

        }catch(ex: NumberFormatException){
            if(newString == ""){
                stackText.text = "0"
            }
        }
    }

    // TODO funkcja, ktora bedzie probowala wrzucic na stos- uwzglednic -<nic>, -liczba
    // TODO jak po enter nie ma enter/dzialania, tylko cyfra, to wykasowac stringa poprzedniego

    fun push(v: View){
        try{
            var number = stackText.text.toString().toDouble()
            stack.push(number)
        }catch(ex: NumberFormatException){

        }
    }

    fun calTwoOperands(v: View){

        var arg2 = stack.pop()
        var arg1 = stack.pop()

        var wynik: Double
        wynik = 0.0

        when(v.id){
            R.id.buttonPLUS -> wynik = arg1 + arg2
            R.id.buttonMINUS -> wynik = arg1 - arg2
            R.id.buttonMULTIPLY -> wynik = arg1 * arg2
            R.id.buttonPOW -> wynik = Math.pow(arg1, arg2)
        }
        if(v.id == R.id.buttonDIVIDE){
            wynik = arg1/arg2
            if(wynik.isNaN()){
                Toast.makeText(this, "Nie wolno dzielić przez 0!", Toast.LENGTH_LONG).show()
                wynik = 0.0
            }
        }

        stackText.text = wynik.toString()
        stack.push(wynik)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState!!.putString("param", stackText.text.toString())
    }
}
