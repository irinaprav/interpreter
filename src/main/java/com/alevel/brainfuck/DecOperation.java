package com.alevel.brainfuck;

public class DecOperation implements Expression {
    @Override
    public void interpret(Context context) {
            context.getData()[context.getResultpointer()]--;
    }
}
