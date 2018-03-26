package com.oliwia.reversepolishnot

import java.util.Stack
import java.util.EmptyStackException

/**
 * Created by Oliwia on 26.03.2018.
 */
class Stack{
    // TODO przerobic na liste
    var stack: Stack<Double> = Stack()
    var elem1 = "0.0"
    var elem2 = "0.0"
    var elem3 = "0.0"


    fun pop(): Double {
        var wynik: Double
        try{
            wynik = stack.pop()
        } catch(ex: EmptyStackException){
            wynik = 0.0
        } finally {

            return wynik
        }

    }

    fun push(e: Double) {
        stack.push(e)
    }

    fun getStrStack(): String{
    }

}