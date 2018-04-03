package com.oliwia.reversepolishnot

import java.util.LinkedList

/**
 * Created by Oliwia on 26.03.2018.
 */

class Stack{
    var stack = LinkedList<Double>()
    var precision:Int = 4

    constructor(stack: LinkedList<Double>, precision: Int) {
        this.stack = stack
        this.precision = precision
    }

    constructor(){

    }


    fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

    val strStack: String
        get() {
            var strStack = ""
            for (i in 3 downTo 1) {
                if (stack.size >= i) {
                    strStack += (i + 1).toString() + ": " + "${stack[i-1].format(precision)}" + "\n"
                } else {
                    val zero = 0.0
                    strStack += (i + 1).toString() + ": "+ "${zero.format(precision)}" + "\n"
                }
            }
            strStack += "1: "
            return strStack
        }

    fun pop():Double{
        if (stack.size > 0) {
            return stack.pop()
        } else {
            return 0.0
        }
    }

    fun push(elem: Double?) {
        stack.push(elem)
    }

    fun swap(){
        var elem1 = this.pop()
        var elem2 = this.pop()
        this.push(elem1)
        this.push(elem2)
    }

    fun emptyStack(){
        stack.clear()
    }

    fun copy(stack: LinkedList<Double> = LinkedList<Double>(this.stack), precision: Int = this.precision) = Stack(stack, precision)


}