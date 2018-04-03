package com.oliwia.reversepolishnot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Oliwia on 26.03.2018.
 */

public class Test {
    private LinkedList<Double> stack = new LinkedList<>();

    public Double pop(){
        if(stack.size() > 0){
            return stack.pop();
        } else{
            return 0.0;
        }
    }

    public void push(Double elem){
        stack.push(elem);
    }

    public String getStrStack(){
        String strStack = "";
        for(int i = 3; i > 0; i--) {
            if (stack.size() >= 3) {
                strStack += (i+1) + ": " + stack.get(i-1) + "\n";
            } else {
                strStack += (i+1) + ": 0.0\n";
            }
        }
        strStack += "1: ";
        return strStack;
    }



}
