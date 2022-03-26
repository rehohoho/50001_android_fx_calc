package com.example.practice;

public class TestFactory {
    public static void main(String[] args) {
        System.out.println(Tea.tehkosong());
        System.out.println(Tea.teh());
    }
}

class Tea {
    private boolean sugar;
    private boolean milk;

    Tea(boolean sugar, boolean milk){
        this.sugar = sugar;
        this.milk = milk;
    }

    public static Tea teh(){
        return new Tea(true, true);
    }

    public static Tea tehkosong(){
        return new Tea(false, true );
    }

    @Override
    public String toString() {
        return "Tea{" +
                "sugar=" + sugar +
                ", milk=" + milk +
                '}';
    }
}

