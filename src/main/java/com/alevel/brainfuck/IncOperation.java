package com.alevel.brainfuck;

public class IncOperation implements Expression {
    @Override
    public void interpret(Context context) {
            context.getData()[context.getResultpointer()]++;
    }
}
