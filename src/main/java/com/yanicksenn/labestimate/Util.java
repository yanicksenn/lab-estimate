package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;
import com.yanicksenn.labestimate.math.Point;

import java.math.BigDecimal;
import java.util.Objects;

public class Util {

    public static Double fromStringToDouble(String number) {
        Objects.requireNonNull(number);

        if (number.isBlank())
            throw new IllegalArgumentException("number must not be blank");

        return new BigDecimal(number).doubleValue();
    }

    public static Point fromLAB(LAB lab) {
        Objects.requireNonNull(lab);
        return Point.of(lab.getL(), lab.getA(), lab.getB());
    }

    private Util() {
        throw new AssertionError();
    }
}
