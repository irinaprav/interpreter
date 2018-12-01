package com.alevel.brainfuck;

public class NextOperation implements Expression {
    @Override
    public void interpret(Context context) {
            int i = context.getResultpointer() + 1;
            context.setResultpointer(i);
    }
}
