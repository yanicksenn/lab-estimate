package com.yanicksenn.labestimate;

import java.math.BigDecimal;
import java.util.Objects;

public class Util {

    public static Double fromStringToDouble(String number) {
        Objects.requireNonNull(number);

        if (number.isBlank())
            throw new IllegalArgumentException("number must not be blank");

        return new BigDecimal(number).doubleValue();
    }

    private Util() {
        throw new AssertionError();
    }
}
