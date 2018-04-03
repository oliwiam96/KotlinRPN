package com.oliwia.reversepolishnot

import java.util.LinkedList

/**
 * Created by Oliwia on 26.03.2018.
 */

class Test2 {
    private val stack = LinkedList<Double>()

    val strStack: String
        get() {
            var strStack = ""
            for (i in 3 downTo 1) {
                if (stack.size >= 3) {
                    strStack += (i + 1).toString() + ": " + stack[i - 1] + "\n"
                } else {
                    strStack += (i + 1).toString() + ": 0.0\n"
                }
            }
            strStack += "1: "
            return strStack
        }

    fun pop(): Double? {
        if (stack.size > 0) {
            return stack.pop()
        } else {
            return 0.0
        }
    }

    fun push(elem: Double?) {
        stack.push(elem)
    }


}
