package com.yanicksenn.labestimate.color;

import java.util.Objects;

import static java.lang.Math.*;

public final class LAB {
    private final double l;
    private final double a;
    private final double b;

    public LAB(double l, double a, double b) {
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

    public XYZ toXYZ(StandardIlluminant standardIlluminant) {
        Objects.requireNonNull(standardIlluminant);

        var l = getL();
        var a = getA();
        var b = getB();

        var fy = (l + 16.0) / 116.0;
        var fx = (a / 500.0) + fy;
        var fz = fy - (b / 200.0);

        var fx3 = pow(fx, 3.0);
        var fz3 = pow(fz, 3.0);

        var e = CIE.E;
        var k = CIE.K;

        var xr = fx3 > e ? fx3 : ((116.0 * fx) - 16.0) / k;
        var yr = l > k * e ? pow((l + 16.0) / 116.0, 3.0) : l / k;
        var zr = fz3 > e ? fz3 : ((116.0 * fz) - 16.0) / k;

        var rw = standardIlluminant.getReferenceWhite();
        var x = xr * rw.getX();
        var y = yr * rw.getY();
        var z = zr * rw.getZ();

        return new XYZ(x, y, z);
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
