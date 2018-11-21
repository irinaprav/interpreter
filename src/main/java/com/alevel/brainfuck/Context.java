package com.alevel.brainfuck;

import java.io.InputStreamReader;

public class Context {

    private String input;
    private String output;
    private byte[] data;
    private int resultpointer;
    private char convertiblepointer;

    private InputStreamReader reader;

    public Context(String input) {
        this.input = input;
        data = new byte[200];
        output = "";
        resultpointer = 0;
        convertiblepointer = 0;
        reader = new InputStreamReader(System.in);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getResultpointer() {
        return resultpointer;
    }

    public void setResultpointer(int resultpointer) {
        this.resultpointer = resultpointer;
    }

    public char getConvertiblepointer() {
        return convertiblepointer;
    }

    public void setConvertiblepointer(char convertiblepointer) {
        this.convertiblepointer = convertiblepointer;
    }


}
