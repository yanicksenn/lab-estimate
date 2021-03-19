package com.yanicksenn.labestimate.color;

import static java.lang.Math.*;

public class DeltaE76 implements IDeltaE {
    @Override
    public double calculate(LAB lab1, LAB lab2) {
        double dL = lab2.getL() - lab1.getL();
        double dA = lab2.getA() - lab1.getA();
        double dB = lab2.getB() - lab1.getB();
        return sqrt(pow(dL, 2) + pow(dA, 2) + pow(dB, 2));
    }
}
