package com.yanicksenn.labestimate.math;

import java.util.Objects;

public class Straight {
    private final Point source;
    private final Point direction;

    public static Straight betweenPoints(Point a, Point b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        return of(a, b.subtract(a));
    }

    public static Straight of(Point source, Point direction) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(direction);
        return new Straight(source, direction);
    }

    public Straight(Point source, Point direction) {
        this.source = Objects.requireNonNull(source);
        this.direction = Objects.requireNonNull(direction);
    }

    public Point getSource() {
        return source;
    }

    public Point getDirection() {
        return direction;
    }

    public Point getPoint(double r) {
        return getSource().add(getDirection().multiply(r));
    }

    public double distanceTo(Point point) {
        var r = stretchTo(point);
        var closestPoint = getPoint(r);
        return closestPoint.distanceTo(point);
    }

    public double stretchTo(Point point) {
        var sp = getSource().subtract(point);
        var sv = sp.multiply(getDirection());
        var rv = getDirection().multiply(getDirection());
        if (rv == 0) {
            return 0;
        }

        return sv / rv;
    }

    @Override
    public String toString() {
        return String.format("Straight{source=%s, direction=%s}", getSource().toString(), getDirection().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Straight straight = (Straight) o;

        return
            Objects.equals(source, straight.source) &&
            Objects.equals(direction, straight.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, direction);
    }
}
