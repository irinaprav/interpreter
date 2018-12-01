package com.alevel.brainfuck;

public class RightbrOperation implements Expression{
    @Override
    public void interpret(Context context) {
            char[] chardata = context.getInput().toCharArray();
                int i = 1;
                while (i > 0) {
                    char m = context.getConvertiblepointer();
                    char c = chardata[--m];
                    context.setConvertiblepointer(m);
                    if (c == '[')
                        i--;
                    else if (c == ']')
                        i++;

                }
                char c = context.getConvertiblepointer();
                c--;
                context.setConvertiblepointer(c);
    }
}
