package com.yanicksenn.labestimate.color;

import java.util.Objects;

public class XY {
    private final double x;
    private final double y;

    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return 1 - getX() - getY();
    }

    @Override
    public String toString() {
        return String.format("XY{x=%f, y=%f}", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        XY that = (XY) o;

        return
            Double.compare(that.x, x) == 0 &&
            Double.compare(that.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
