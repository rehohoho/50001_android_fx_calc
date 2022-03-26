package com.example.practice;

public class TestBuilder {
    public static void main(String[] args) {
        TeaTwo teaTwo = new
            TeaTwo.TeaBuilder().setSugar(true).setMilk(true).build();
        System.out.println(teaTwo);
    }
}

class TeaTwo {
    private boolean sugar;
    private boolean milk;

    private TeaTwo(TeaBuilder teaBuilder){
        this.sugar = teaBuilder.sugar;
        this.milk = teaBuilder.milk;
    }

    static class TeaBuilder{
        private boolean sugar;
        private boolean milk;

        TeaBuilder(){}

        public TeaBuilder setSugar(boolean sugar) {
            this.sugar = sugar;
            return this;
        }

        public TeaBuilder setMilk(boolean milk) {
            this.milk = milk;
            return this;
        }

        public TeaTwo build() {
            return new TeaTwo(this);
        }
    }

    @Override
    public String toString() {
        return "TeaTwo{" +
                "sugar=" + sugar +
                ", milk=" + milk +
                '}';
    }
}