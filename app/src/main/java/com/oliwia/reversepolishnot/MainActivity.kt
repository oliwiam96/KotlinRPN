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
    var lastElem:String = ""

    fun updateStr(){
        stackText.text = stack.strStack + lastElem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState!= null){
            lastElem =savedInstanceState.getString("param") ?: ""
            lastElem = "0" // UWAGA!  ODKOMENTOWAĆ
        } else{
            lastElem = "0"
        }
        updateStr()
    }

    fun changeSign(v: View){
        if(lastElem.equals("0")){
            return;
        }
        else if(lastElem[0] == '-'){ // change to +
            lastElem = lastElem.substring(1, lastElem.length)
        } else{ // change to -
            lastElem = '-' +  lastElem
        }
        updateStr()
    }

    fun swap(v: View){
        stack.push(lastElem.toDouble()) // TODO wyjatek?
        stack.swap()
        lastElem = stack.pop().toString()
        updateStr()
    }

    fun drop(v: View){
        stack.pop()
        updateStr()
    }

    fun click(v: View){
        // TODO C gdy -<nic>
        var newString = ""
        when(v.id){
            R.id.button0 -> newString = lastElem + "0"
            R.id.button1 -> newString = lastElem + "1"
            R.id.button2 -> newString = lastElem + "2"
            R.id.button3 -> newString = lastElem + "3"
            R.id.button4 -> newString = lastElem + "4"
            R.id.button5 -> newString = lastElem + "5"
            R.id.button6 -> newString = lastElem + "6"
            R.id.button7 -> newString = lastElem + "7"
            R.id.button8 -> newString = lastElem + "8"
            R.id.button9 -> newString = lastElem + "9"
            R.id.buttonDOT -> newString = lastElem + "."
            R.id.buttonC -> newString = lastElem.substring(0, lastElem.length - 1)
        }
        try{
            newString.toDouble()
            if(newString.length > 1 && newString[0] == '0' && newString[1] != '.'){
                newString = newString.substring(1, newString.length) //delete first zero
            }
            lastElem = newString

        }catch(ex: NumberFormatException){
            if(newString == ""){
                lastElem = "0"
            }
        }
        updateStr()
    }

    // TODO funkcja, ktora bedzie probowala wrzucic na stos- uwzglednic -<nic>, -liczba
    // TODO jak po enter nie ma enter/dzialania, tylko cyfra, to wykasowac stringa poprzedniego

    fun push(v: View){
        try{
            var number = lastElem.toDouble()
            stack.push(number)
            updateStr()
        }catch(ex: NumberFormatException){
            Toast.makeText(this, "Niepoprawny format ostatniej liczby na stosie, operacja anulowana!", Toast.LENGTH_LONG).show()
        }
    }

    fun calTwoOperands(v: View){
        try{
            var number = lastElem.toDouble()
            stack.push(number)

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
                    Toast.makeText(this, "Nie wolno dzielić przez 0! Ustawiam wynik na 0.0", Toast.LENGTH_LONG).show()
                    wynik = 0.0
                }
            }

            lastElem = wynik.toString()
            updateStr()

        }catch(ex: NumberFormatException){
            Toast.makeText(this, "Niepoprawny format ostatniej liczby na stosie, operacja anulowana!", Toast.LENGTH_LONG).show()
        }


    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState!!.putString("param", stackText.text.toString())
    }
}
