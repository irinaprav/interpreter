package com.alevel.brainfuck;

public class IncOperation implements Expression {
    @Override
    public void interpret(Context context) {
        if (context.getInput().charAt(context.getConvertiblepointer()) == '+'){
            System.out.println("yes+");
            context.getData()[context.getResultpointer()]++;
            System.out.println("yes+");
        }
    }
}
