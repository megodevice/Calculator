package com.geeks.calculator;

public enum Operations {
    SUM ("+"), DIVIDE ("/"), MULTIPLY ("*"), SUBTRACT ("-"), NON ("");

    private String s;

    Operations(String s) {
        this.s = s;
    }

    String getString() {
        return s;
    }
}
