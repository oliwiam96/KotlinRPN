package com.oliwia.reversepolishnot

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 10000

    var stack:Stack = Stack(LinkedList<Double>(), 4)
    var lastElem:String = "0"
    var colorInt:Int = android.graphics.Color.rgb(255, 255, 255)
    var precision:Int = 4
    var previousStack: Stack = Stack(LinkedList<Double>(), 4)
    var previousLastElem: String = "0"
    var lastButtonWasEnter = false
    var lastButtonWasArithmeticOp = false

    fun updateStr(){
        stackText.text = stack.strStack + lastElem
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState!= null){

            lastElem =savedInstanceState.getString("lastElem") ?: "0"
            previousLastElem =savedInstanceState.getString("previousLastElem") ?: "0"
            colorInt =savedInstanceState.getInt("colorInt") ?: android.graphics.Color.rgb(255, 255, 255)
            precision =savedInstanceState.getInt("precision") ?: 4
            stack =  savedInstanceState.getSerializable("stack") as Stack
            previousStack = savedInstanceState.getSerializable("previousStack") as Stack
            lastButtonWasArithmeticOp = savedInstanceState.getBoolean("lastButtonWasArithmeticOp")
            lastButtonWasEnter = savedInstanceState.getBoolean("lastButtonWasEnter")
            stackText.setBackgroundColor(colorInt)
            updateStr()
        }

        updateStr()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.mymenu, menu)
        return true
    }

    fun showActivity(){
        val i = Intent(this, SettingsActivity::class.java)
        i.putExtra("Parametr", "Twoje dane")
        startActivityForResult(i, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if((requestCode == REQUEST_CODE)
                && (resultCode == Activity.RESULT_OK)){
            if(data != null){
                if(data.hasExtra("colorInt")){
                    colorInt =  data.extras.getString("colorInt").toInt()
                    stackText.setBackgroundColor(colorInt)
                }
                if(data.hasExtra("precision")){
                    precision = data.extras.getString("precision").toInt()
                    stack.precision = precision
                    updateStr()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                showActivity()
                true
            }

            R.id.about -> {
                Toast.makeText(this, "Oliwia Masian\n127324", Toast.LENGTH_LONG).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }



    fun changeSign(v: View){
        saveInstance()
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
        saveInstance()
        stack.push(lastElem.toDouble())
        stack.swap()
        lastElem = stack.pop().toString()
        updateStr()
    }

    fun drop(v: View){
        saveInstance()
        lastElem = stack.pop().toString()
        updateStr()
    }

    fun emptyStack(v: View){
        saveInstance()
        stack.emptyStack()
        lastElem = "0"
        updateStr()
    }

    fun saveInstance(){
        previousLastElem = lastElem
        previousStack = stack.copy()
    }

    fun undo(v: View){
        lastElem = previousLastElem
        stack = previousStack
        updateStr()
    }


    fun click(v: View){
        var newString = ""
        if(lastButtonWasArithmeticOp){ // wrzuc wynik na stos (wyzej) i zacznij pisac od nowa
            lastButtonWasArithmeticOp = false
            this.push()
            lastElem = "0"
        }
        if(lastButtonWasEnter){
            lastButtonWasEnter = false
            lastElem = "0"
        }
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
            if(newString == "" || newString == "-"){
                lastElem = "0"
            }
        }
        updateStr()
    }

    fun pushEnter(v: View){
        lastButtonWasEnter = true
        push()
    }

    fun push(){
        saveInstance()
        try{
            var number = lastElem.toDouble()
            stack.push(number)
            updateStr()
        }catch(ex: NumberFormatException){
            Toast.makeText(this, "Niepoprawny format ostatniej liczby na stosie, operacja anulowana!", Toast.LENGTH_LONG).show()
        }
    }



    fun sqrt(v: View){
        lastButtonWasArithmeticOp = true
        saveInstance()
        try{
            var number = lastElem.toDouble()

            var wynik: Double
            wynik = Math.sqrt(number)

            if(wynik.isNaN() || wynik.isInfinite()){
                Toast.makeText(this, "Nie można oblczyć pierwiastka z ujemnej liczby! Operacja anulowana!", Toast.LENGTH_LONG).show()
            } else{
                lastElem = wynik.toString()
            }
            updateStr()

        }catch(ex: NumberFormatException){
            Toast.makeText(this, "Niepoprawny format ostatniej liczby na stosie, operacja anulowana!", Toast.LENGTH_LONG).show()
        }

    }

    fun calTwoOperands(v: View){
        lastButtonWasArithmeticOp = true
        saveInstance()
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
                R.id.buttonDIVIDE -> wynik = arg1/arg2
            }
            if(wynik.isNaN() || wynik.isInfinite()){
                Toast.makeText(this, "Nie wolno dzielić przez 0! Operacja anulowana!", Toast.LENGTH_LONG).show()
                stack.push(arg1)
            } else{
                lastElem = wynik.toString()
            }
            updateStr()

        }catch(ex: NumberFormatException){
            Toast.makeText(this, "Niepoprawny format ostatniej liczby na stosie, operacja anulowana!", Toast.LENGTH_LONG).show()
        }


    }

    override fun onSaveInstanceState(savedInstanceState: Bundle?) {

        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState!!.putString("lastElem", lastElem)
        savedInstanceState!!.putString("previousLastElem", previousLastElem)
        savedInstanceState!!.putInt("precision", precision)
        savedInstanceState!!.putInt("colorInt", colorInt)
        savedInstanceState!!.putSerializable("stack", stack)
        savedInstanceState!!.putSerializable("previousStack", previousStack)
        savedInstanceState!!.putBoolean("lastButtonWasArithmeticOp", lastButtonWasArithmeticOp)
        savedInstanceState!!.putBoolean("lastButtonWasEnter", lastButtonWasEnter)

    }
}
