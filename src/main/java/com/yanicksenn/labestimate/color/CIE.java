package com.yanicksenn.labestimate.color;

public class CIE {
    public static final double E = 0.008856; //216.0 / 24389.0;
    public static final double K = 903.3; // 24389.0 / 27.0;

    private CIE() {
        throw new AssertionError();
    }
}
