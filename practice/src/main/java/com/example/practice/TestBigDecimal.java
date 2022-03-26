package com.example.practice;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TestBigDecimal {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("4");
        BigDecimal c = a.divide(b, RoundingMode.HALF_UP);
        System.out.println(c);
        System.out.println("Scale:" + c.scale() );
        System.out.println("Unscaled value:" + c.unscaledValue());

        BigDecimal up = new BigDecimal("10");
        BigDecimal down = new BigDecimal("7");
        int sigFigures = 5;
        MathContext mc = new MathContext(sigFigures,RoundingMode.HALF_UP);
        BigDecimal result = up.divide(down,mc);
        System.out.println(result);
    }
}
