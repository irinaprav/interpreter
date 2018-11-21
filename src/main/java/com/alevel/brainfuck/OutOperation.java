package com.alevel.brainfuck;

public class OutOperation implements Expression {
    @Override
    public void interpret(Context context) {
        if (context.getInput().charAt(context.getConvertiblepointer()) == '.'){
            System.out.println("yes.");
            context.setOutput(context.getOutput()+(char)context.getData()[context.getResultpointer()]);

        }
    }
}
