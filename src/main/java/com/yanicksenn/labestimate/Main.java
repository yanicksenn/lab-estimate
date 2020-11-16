package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            estimate(args);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    private static void estimate(String[] args) throws IOException {
        if (args.length != 4)
            throw new IllegalArgumentException("args must be of length 4");

        var referenceFile = new File(args[0]);
        var l = createAndValidateValueL(args[1]);
        var a = createAndValidateValueA(args[2]);
        var b = createAndValidateValueB(args[3]);

        var labEstimate = new LABEstimate(referenceFile);
        var referencePair = labEstimate.estimate(new LAB(l, a, b));

        System.out.println(String.format("LAB 1: %s", referencePair.getReferenceA().getLabTarget()));
        System.out.println(String.format("LAB 2: %s", referencePair.getReferenceB().getLabTarget()));
        System.out.println(String.format("Distance: %f", referencePair.getDistance()));
        System.out.println(String.format("Accuracy: %f", referencePair.getAccuracy()));
        System.out.println(String.format("Error: %f", referencePair.getError()));
        System.out.println(String.format("LAB Est: %s", referencePair.getEstimate()));
    }

    private static double createAndValidateValueL(String lValue) {
        try {
            return Util.fromStringToDouble(lValue);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("lValue '%s' must be a number", lValue));
        }
    }

    private static double createAndValidateValueA(String aValue) {
        try {
            return Util.fromStringToDouble(aValue);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("aValue '%s' must be a number", aValue));
        }
    }

    private static double createAndValidateValueB(String bValue) {
        try {
            return Util.fromStringToDouble(bValue);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(String.format("bValue '%s' must be a number", bValue));
        }
    }
}
