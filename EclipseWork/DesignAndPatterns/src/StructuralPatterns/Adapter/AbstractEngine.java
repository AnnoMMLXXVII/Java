package StructuralPatterns.Adapter;

import Introduction.Engine;

public abstract class AbstractEngine implements Engine {
    private int size;
    private boolean turbo;

    public AbstractEngine(int size, boolean turbo) {
        this.size = size;
        this.turbo = turbo;
    } // endae CONSTRUCTOR

    public int getSize() {
        return size;
    } // endae Accessor getSize

    public boolean isTurbo() {
        return turbo;
    } // endae Accessor isTurbo

    public String toString() {
        return getClass().getSimpleName() +
                "(" + size + ")";
    } // endae Accessor toString
} // endae CLASS
