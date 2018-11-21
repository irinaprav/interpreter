package com.alevel.brainfuck;

public class PrevOperation implements Expression {
    @Override
    public void interpret(Context context) {
        if (context.getInput().charAt(context.getConvertiblepointer()) == '<'){
            int i = context.getResultpointer() - 1;
            context.setResultpointer(i);
        }
    }
}
