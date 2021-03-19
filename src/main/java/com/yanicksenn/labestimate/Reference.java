package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.CMYK;
import com.yanicksenn.labestimate.color.LAB;

import java.util.Objects;

public class Reference {
    private final LAB labSource;
    private final LAB labTarget;

    public static Reference ofString(String line) {
        var lineElements = line.split(",");

        try {
            var lSource = Util.fromStringToDouble(lineElements[0]);
            var aSource = Util.fromStringToDouble(lineElements[1]);
            var bSource = Util.fromStringToDouble(lineElements[2]);
            var labSource = new LAB(lSource, aSource, bSource);

            var lTarget = Util.fromStringToDouble(lineElements[3]);
            var aTarget = Util.fromStringToDouble(lineElements[4]);
            var bTarget = Util.fromStringToDouble(lineElements[5]);
            var labTarget = new LAB(lTarget, aTarget, bTarget);

            return new Reference(labSource, labTarget);
        } catch (Exception e) {
            System.err.println(line);
            throw e;
        }
    }

    public Reference(LAB labSource, LAB labTarget) {
        this.labSource = labSource;
        this.labTarget = labTarget;
    }

    public LAB getLabSource() {
        return labSource;
    }

    public LAB getLabTarget() {
        return labTarget;
    }

    @Override
    public String toString() {
        return String.format("Reference{labSource=%s, labTarget=%s}",
            getLabSource().toString(), getLabTarget().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Reference reference = (Reference) o;

        return
            Objects.equals(labSource, reference.labSource) &&
            Objects.equals(labTarget, reference.labTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(labSource, labTarget);
    }
}
