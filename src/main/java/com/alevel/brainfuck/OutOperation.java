package com.alevel.brainfuck;

public class OutOperation implements Expression {
    @Override
    public void interpret(Context context) {
            context.setOutput(context.getOutput()+(char)context.getData()[context.getResultpointer()]);
        }
}
