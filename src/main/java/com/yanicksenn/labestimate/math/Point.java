package com.yanicksenn.labestimate.math;

import java.util.Objects;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Point {
    private final double x;
    private final double y;
    private final double z;

    public static Point of(double x, double y, double z) {
        return new Point(x, y, z);
    }

    public Point(double x, double y, double z) {
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

    public Point add(Point point) {
        Objects.requireNonNull(point);
        return new Point(
            point.getX() + getX(),
            point.getY() + getY(),
            point.getZ() + getZ());
    }

    public Point subtract(Point point) {
        Objects.requireNonNull(point);
        return new Point(
            point.getX() - getX(),
            point.getY() - getY(),
            point.getZ() - getZ());
    }

    public Point multiply(double value) {
        return new Point(
            getX() * value,
            getY() * value,
            getZ() * value);
    }

    public double multiply(Point point) {
        return
            getX() * point.getX() +
            getY() * point.getY() +
            getZ() * point.getZ();
    }

    public double distanceTo(Point point) {
        return sqrt(
            pow(point.getX() - getX(), 2) +
            pow(point.getY() - getY(), 2) +
            pow(point.getZ() - getZ(), 2));
    }

    @Override
    public String toString() {
        return String.format("Point{x=%f, y=%f, z=%f}",
            getX(), getY(), getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Point point = (Point) o;

        return
            Double.compare(point.x, x) == 0 &&
            Double.compare(point.y, y) == 0 &&
            Double.compare(point.z, z) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
