package com.g13.model.arena;

public class Bar {
    private int value;
    private int max_value;

    public Bar(int value, int max_value) {
        this.value = value;
        this.max_value = max_value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }
}
