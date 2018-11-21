package com.alevel.brainfuck;

import java.io.InputStreamReader;

public class BrainfuckInterpreter {
    public final static char NEXT = '>';
    public final static char PREV = '<';
    public final static char INC = '+';
    public final static char DEC = '-';
    public final static char LEFTBR = '[';
    public final static char RIGHTBR = ']';
    public final static char OUT = '.';
    public final static char IN = ',';

    private byte[] data;
    private String result = "";
    private int resultpointer;
    private char convertiblepointer;
    InputStreamReader reader;

    BrainfuckInterpreter(int amount) {
        data = new byte[amount];
        resultpointer = 0;
        convertiblepointer = 0;
        reader = new InputStreamReader(System.in);
    }

    public String interpreter(String convertible) throws Exception {
        for (; convertiblepointer < convertible.length(); convertiblepointer++)
            interpreter(convertible.charAt(convertiblepointer), convertible.toCharArray());
        return result;

    }

    public void interpreter(char operand, char[] chardata) throws Exception {
        switch (operand) {
            case NEXT:
                resultpointer++;
                break;
            case PREV:
                resultpointer--;
                break;
            case INC:
                data[resultpointer]++;
                break;
            case DEC:
                data[resultpointer]--;
                break;
            case LEFTBR:
                if (data[resultpointer] == 0) {
                    int i = 1;
                    while (i > 0) {
                        char c = chardata[++convertiblepointer];
                        if (c == LEFTBR)
                            i++;
                        else if (c == RIGHTBR)
                            i--;

                    }
                }
                break;
            case RIGHTBR:
                int i = 1;
                while (i > 0) {
                    char c = chardata[--convertiblepointer];
                    if (c == LEFTBR)
                        i--;
                    else if (c == RIGHTBR)
                        i++;

                }
                convertiblepointer--;
                break;
            case OUT:
                result += (char) data[resultpointer];
                break;
            case IN:
                data[resultpointer] = (byte) reader.read();
                break;
        }

    }

}