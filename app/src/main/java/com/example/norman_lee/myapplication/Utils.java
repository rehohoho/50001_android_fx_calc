package com.example.norman_lee.myapplication;

public class Utils {

    static void checkInvalidInputs(String value) {
        try {
            Double doubValue = Double.parseDouble(value);
            if (doubValue <= 0.0) {
                throw new IllegalArgumentException(String.format("value < 0, got %s", value));
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

}
