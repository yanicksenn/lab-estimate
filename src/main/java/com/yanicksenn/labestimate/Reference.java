package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.CMYK;
import com.yanicksenn.labestimate.color.LAB;

import java.util.Objects;

public class Reference {
    private final CMYK cmyk;
    private final LAB labSource;
    private final LAB labTarget;

    public static Reference ofString(String line) {
        var lineElements = line.split(",");

        var c = Util.fromStringToDouble(lineElements[0]);
        var m = Util.fromStringToDouble(lineElements[1]);
        var y = Util.fromStringToDouble(lineElements[2]);
        var k = Util.fromStringToDouble(lineElements[3]);
        var cmyk = new CMYK(c, m, y, k);

        var lSource = Util.fromStringToDouble(lineElements[4]);
        var aSource = Util.fromStringToDouble(lineElements[5]);
        var bSource = Util.fromStringToDouble(lineElements[6]);
        var labSource = new LAB(lSource, aSource, bSource);

        var lTarget = Util.fromStringToDouble(lineElements[7]);
        var aTarget = Util.fromStringToDouble(lineElements[8]);
        var bTarget = Util.fromStringToDouble(lineElements[9]);
        var labTarget = new LAB(lTarget, aTarget, bTarget);

        return new Reference(cmyk, labSource, labTarget);
    }

    public Reference(CMYK cmyk, LAB labSource, LAB labTarget) {
        this.cmyk = cmyk;
        this.labSource = labSource;
        this.labTarget = labTarget;
    }

    public CMYK getCmyk() {
        return cmyk;
    }

    public LAB getLabSource() {
        return labSource;
    }

    public LAB getLabTarget() {
        return labTarget;
    }

    @Override
    public String toString() {
        return String.format("Reference{cmyk=%s, labSource=%s, labTarget=%s}",
            getCmyk().toString(), getLabSource().toString(), getLabTarget().toString());
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
            Objects.equals(cmyk, reference.cmyk) &&
            Objects.equals(labSource, reference.labSource) &&
            Objects.equals(labTarget, reference.labTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmyk, labSource, labTarget);
    }
}
