package com.yanicksenn.labestimate.color;

import com.yanicksenn.labestimate.Util;

import java.util.Objects;

public final class LAB {
    private final double l;
    private final double a;
    private final double b;

    public LAB(double l, double a, double b) {
        if (l > 100)
            throw new IllegalArgumentException("l must not be greater than 100");

        if (l < 0)
            throw new IllegalArgumentException("l must not be less than 100");

        if (a > 127)
            throw new IllegalArgumentException("a must not be greater than 127");

        if (a < -128)
            throw new IllegalArgumentException("a must not be less than -128");

        if (b > 127)
            throw new IllegalArgumentException("b must not be greater than 127");

        if (b < -128)
            throw new IllegalArgumentException("b must not be less than -128");

        this.l = l;
        this.a = a;
        this.b = b;
    }

    public double getL() {
        return l;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double distanceTo(LAB lab) {
        Objects.requireNonNull(lab);
        var p1 = Util.fromLAB(this);
        var p2 = Util.fromLAB(lab);
        return p1.distanceTo(p2);
    }

    @Override
    public String toString() {
        return String.format("LAB{l=%f, a=%f, b=%f}",
            getL(), getA(), getB());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        LAB lab = (LAB) o;

        return
            Double.compare(lab.l, l) == 0 &&
            Double.compare(lab.a, a) == 0 &&
            Double.compare(lab.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(l, a, b);
    }
}
