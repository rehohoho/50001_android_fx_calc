package com.example.practice;

public class TestExceptions {
    public static void main(String[] args) {
        try {
            int a = quotientInt(5,0);
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }

    public static int quotientInt(int a, int b){
        return a / b;
    }

    public static double quotientDouble(double a, double b){
        return a/b;
    }
}
