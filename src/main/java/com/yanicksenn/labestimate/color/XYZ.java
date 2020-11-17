package com.yanicksenn.labestimate.color;

import java.util.Objects;

import static java.lang.Math.*;

public class XYZ {
    private final double x;
    private final double y;
    private final double z;

    public XYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double distanceTo(XYZ xyz) {
        Objects.requireNonNull(xyz);

        return sqrt(
            pow(xyz.getX() - getX(), 2) +
            pow(xyz.getY() - getY(), 2) +
            pow(xyz.getZ() - getZ(), 2));
    }

    public LAB toLAB(StandardIlluminant standardIlluminant) {
        Objects.requireNonNull(standardIlluminant);

        var e = CIE.E;
        var k = CIE.K;

        var rw = standardIlluminant.getReferenceWhite();
        var xr = getX() / rw.getX();
        var yr = getY() / rw.getY();
        var zr = getZ() / rw.getZ();

        var fx = xr > e ? pow(xr, 1.0 / 3.0) : (k * xr + 16.0) / 116.0;
        var fy = yr > e ? pow(yr, 1.0 / 3.0) : (k * yr + 16.0) / 116.0;
        var fz = zr > e ? pow(zr, 1.0 / 3.0) : (k * zr + 16.0) / 116.0;

        var l = (116.0 * fy) - 16.0;
        var a = 500.0 * (fx - fy);
        var b = 200.0 * (fy - fz);

        return new LAB(l, a, b);
    }

    @Override
    public String toString() {
        return String.format("XYZ{x=%f, y=%f, z=%f}", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        XYZ xyz = (XYZ) o;

        return
            Double.compare(xyz.x, x) == 0 &&
            Double.compare(xyz.y, y) == 0 &&
            Double.compare(xyz.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
