package com.example.norman_lee.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    //TODO 5.4 Write unit tests to check the ExchangeRate class
    @Test
    public void exchangeRateDefaultRate() {
        String defaultExchangeRate = "2.95000";
        assertEquals(defaultExchangeRate, new ExchangeRate().getExchangeRate().toString());
    }

    @Test
    public void exchangeRateCalculateAmount() {
        assertTrue(new ExchangeRate().calculateAmount("123") instanceof BigDecimal);
    }

    @Test(expected = NumberFormatException.class)
    public void exchangeRateEmptyField() {
        new ExchangeRate("");
    }
}