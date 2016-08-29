package com.touchmenotapps.flashy.dao.enums;

/**
 * Created by arindamnath on 18/01/16.
 */
public enum LoaderID {
    FEATURED_LESSOSN(1),
    LESSONS(2);

    private final int value;
    LoaderID (final int newValue) {
        value = newValue;
    }
    public int getValue() { return value; }
}
