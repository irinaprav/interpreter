package com.alevel.brainfuck;

public class DecOperation implements Expression {
    @Override
    public void interpret(Context context) {
        if (context.getInput().charAt(context.getConvertiblepointer()) == '-'){
            context.getData()[context.getResultpointer()]--;
        }
    }
}
