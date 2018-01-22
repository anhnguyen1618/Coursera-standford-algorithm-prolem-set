/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zozo.calculator.model;

import java.util.Arrays;

/**
 *
 * 
 * @author Jarkko
 */
public class Bignum {
    private byte[] number;          // least significand digit first (index 0), most significand last (index length-1)
    private static int mulCounter;  // variable to count the number of multiplications

    public Bignum(int n) {
        number = new byte[n];
    }
    
    public Bignum(String s) {
        int     n = s.length();
        number = new byte[n];

        for (int i = n-1; i >= 0; i--)
            number[n-i-1] = (byte)Character.getNumericValue(s.charAt(i));
    }
    
    public void bigNum(String s) {
        int     n = s.length();
        number = new byte[n];

        for (int i = n-1; i >= 0; i--)
            number[n-i-1] = (byte)Character.getNumericValue(s.charAt(i));
    }

    
    /* print out the number to the string s */
    public String toString() {
        StringBuilder s = new StringBuilder();
        
        for (int i = number.length-1; i >= 0; i--)
            s.append(number[i]);
        
        return (s.toString());
    }


    /* print out the given number (for debug only) */
    public void printBigNum(String s) {
        System.out.println(s + ": " + toString());
    }


    /* create a new number whose digits are x[from, to) */
    public Bignum selectBigNum(int from, int to) {
        Bignum r = new Bignum(to-from);

        for (int i = from; i < to; i++){
            r.number[i-from] = number[i];
        }
            
        return r;
    }


    /* subtract two numbers this - y */
    public Bignum subBigNum(Bignum y) throws Exception {      
        Bignum r = new Bignum(number.length);      
        int    carry;

        // sub digits, starting from the least significant digit
        carry = 0;
        for (int i = 0; i < number.length; i++) {
            r.number[i] = (byte)(number[i] - (i < y.number.length ? y.number[i] : 0) - carry);
            if (r.number[i] < 0) {
                carry = 1;
                r.number[i] += 10;
            } else
                carry = 0;
        }

        if (carry > 0) {
            throw new Exception("subtract over flow");
        }

        return r;
    }


    /* add two numbers together this + y */
    public Bignum addBigNum(Bignum y) {
        Bignum r, a, b;
        int    carry;

        // a is the larger number, b is the smaller
        if (number.length > y.number.length) {
            a = this; b = y;
        } else {
            a = y; b = this;
        }

        r = new Bignum(a.number.length);

        // add digits, starting from the least significant digit
        carry = 0;
        for (int i = 0; i < a.number.length; i++) {
            r.number[i] = (byte)(a.number[i] + (i < b.number.length ? b.number[i] : 0) + carry);
            if (r.number[i] > 9) {
                carry = 1;
                r.number[i] -= 10;
            } else
                carry = 0;
        }

        if (carry > 0) {
            r.number = Arrays.copyOf(r.number, r.number.length+1);
            r.number[r.number.length-1] = 1;
        }

        return r;
    }
    
    public void balanceLength(Bignum y) {
        
        int lengthGap = number.length - y.number.length;
        
        if (lengthGap == 0) {
            return;
        }
        
        String insertedString = this.generateZeroSequence(lengthGap > 0 ? lengthGap : -lengthGap);
        
        if (lengthGap > 0) {
            Bignum tempNum = new Bignum(insertedString + y.toString());
            y.number = tempNum.number;
        } else {
            Bignum tempNum = new Bignum(insertedString + toString());
            number = tempNum.number;
        }
    }
    
    public static String generateZeroSequence(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += "0";
        }
        return result;
    }
    
    public Bignum multiPly(Bignum y) throws Exception {
        Bignum tempResult = this.mulBigNum(y);
        
        String tempResultString = tempResult.toString();
        String result = "";
        
        boolean begin = false;
        for (int i = 0; i < tempResultString.length(); i++) {
            char currChar = tempResultString.charAt(i);
            if (begin) {
                result += currChar;
            } else {
                if (currChar != '0') {
                    result += currChar;
                    begin = true;
                }
            }
        }
        
        return new Bignum(result);
    }


    /* multiply two numbers (this * y) together using divide-and-conquer technique */
    public Bignum mulBigNum(Bignum y) throws Exception {
        balanceLength(y);
        
        if (number.length == 0 || y.number.length == 0){
            return new Bignum("1");
        }
        
        
        if (y.number.length == 1 && number.length == 1){
            int a = number[0];
            int b = y.number[0];

            return new Bignum(Integer.toString(a * b));
        }
        
        if (y.number.length % 2 != 0) {
            y = new Bignum("0" + y.toString());
        }
        
        if (number.length % 2 != 0) {
            number = (new Bignum("0" + toString())).number;
        }


        
        Bignum xRight = selectBigNum(0, number.length / 2);
        Bignum xLeft = selectBigNum(number.length / 2, number.length);
        
        Bignum yRight = y.selectBigNum(0, y.number.length / 2);
        Bignum yLeft = y.selectBigNum(y.number.length / 2, y.number.length);
        

        
        Bignum p1 = xLeft.mulBigNum(yLeft);
        Bignum p2 = xRight.mulBigNum(yRight);
        
        Bignum a = xLeft.addBigNum(xRight);
        Bignum b = yLeft.addBigNum(yRight);
        Bignum p3 = a.mulBigNum(b);
        
        Bignum result = new Bignum(p1.toString() + this.generateZeroSequence(number.length))
                .addBigNum(new Bignum(p3.subBigNum(p1).subBigNum(p2).toString() + this.generateZeroSequence(number.length/2)))
                .addBigNum(p2);
        
        return result;
    }
    
    public static String generateZero(int n) {
        String result = "";
        
        for (int i = 0; i < n; i++){
            result += "0";
        }
        
        return result;
    }


    public void clrMulCounter() {
        mulCounter = 0;
    }


    public int rclMulCounter() {
        return (mulCounter);
    }
}

