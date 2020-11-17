package com.yanicksenn.labestimate.color;

import java.util.Objects;

public class StandardIlluminant {
    private final String name;
    private final XY xy;
    private final int cct;

    public StandardIlluminant(String name, XY xy, int cct) {
        this.name = name;
        this.xy = xy;
        this.cct = cct;
    }

    public String getName() {
        return name;
    }

    public XY getXy() {
        return xy;
    }

    public int getCct() {
        return cct;
    }

    public XYZ getReferenceWhite() {
        var xy = getXy();

        var ix = xy.getX();
        var iy = xy.getY();
        var iz = xy.getZ();

        var y = 1.0;
        var x = ix * y / iy;
        var z = iz * y / iy;

        return new XYZ(x, y, z);
    }

    @Override
    public String toString() {
        return String.format("StandardIlluminant{name=%s, xy=%s, cct=%d}", getName(), getXy(), getCct());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        StandardIlluminant that = (StandardIlluminant) o;

        return
            cct == that.cct &&
            Objects.equals(name, that.name) &&
            Objects.equals(xy, that.xy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, xy, cct);
    }
}
