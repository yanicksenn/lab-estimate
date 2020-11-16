package com.yanicksenn.labestimate.color;

import java.util.Objects;

public class CMYK {
    private final double c;
    private final double m;
    private final double y;
    private final double k;

    public CMYK(double c, double m, double y, double k) {
        this.c = c;
        this.m = m;
        this.y = y;
        this.k = k;
    }

    public double getC() {
        return c;
    }

    public double getM() {
        return m;
    }

    public double getY() {
        return y;
    }

    public double getK() {
        return k;
    }

    @Override
    public String toString() {
        return String.format("CMYK{c=%f, m=%f, y=%f, k=%f}",
            getC(), getM(), getY(), getK());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        CMYK cmyk = (CMYK) o;

        return
            Double.compare(cmyk.c, c) == 0 &&
            Double.compare(cmyk.m, m) == 0 &&
            Double.compare(cmyk.y, y) == 0 &&
            Double.compare(cmyk.k, k) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(c, m, y, k);
    }
}
