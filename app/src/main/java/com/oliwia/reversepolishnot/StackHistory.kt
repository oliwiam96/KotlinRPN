package com.oliwia.reversepolishnot

import java.io.Serializable
import java.util.*
import java.util.NoSuchElementException


/**
 * Created by Oliwia on 09.04.2018.
 */

class StackHistory: Serializable{
    var historyStack = LinkedList<Stack>()
    var historyLastElem = LinkedList<String>()
    val MAX_SIZE = 10

    @Throws(NoSuchElementException::class)
    fun popStack():Stack {
        return historyStack.pop()
    }

    @Throws(NoSuchElementException::class)
    fun popLastElem():String {
        return historyLastElem.pop()
    }

    fun push(stack: Stack, lastElem: String){
        if(historyStack.size >= MAX_SIZE){
            historyStack.removeLast()
            historyLastElem.removeLast()
        }
        historyStack.push(stack)
        historyLastElem.push(lastElem)
    }

}