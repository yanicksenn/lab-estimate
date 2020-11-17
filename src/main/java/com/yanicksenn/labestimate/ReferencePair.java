package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;
import com.yanicksenn.labestimate.math.Straight;

import java.util.Objects;

public class ReferencePair {
    private final LAB labToEstimate;
    private final Reference referenceA;
    private final Reference referenceB;

    public ReferencePair(LAB labToEstimate, Reference referenceA, Reference referenceB) {
        this.labToEstimate = Objects.requireNonNull(labToEstimate);
        this.referenceA = Objects.requireNonNull(referenceA);
        this.referenceB = Objects.requireNonNull(referenceB);
    }

    public Reference getReferenceA() {
        return referenceA;
    }

    public Reference getReferenceB() {
        return referenceB;
    }

    public double getSourceDistance() {
        var p1 = Util.fromLAB(referenceA.getLabSource());
        var p2 = Util.fromLAB(referenceB.getLabSource());
        return p1.distanceTo(p2);
    }

    public double getSourceOffset() {
        var p1 = Util.fromLAB(referenceA.getLabSource());
        var p2 = Util.fromLAB(referenceB.getLabSource());
        var straight = Straight.betweenPoints(p1, p2);
        return straight.distanceTo(Util.fromLAB(labToEstimate));
    }

    public boolean inBounds() {
        var p1 = Util.fromLAB(referenceA.getLabSource());
        var p2 = Util.fromLAB(referenceB.getLabSource());
        var straight = Straight.betweenPoints(p1, p2);
        return straight.stretchTo(Util.fromLAB(labToEstimate)) <= 1;
    }

    public double getSourceError() {
        return getSourceDistance() * getSourceOffset();
    }

    public double getTargetDistance() {
        var p1 = Util.fromLAB(referenceA.getLabTarget());
        var p2 = Util.fromLAB(referenceB.getLabTarget());
        return p1.distanceTo(p2);
    }

    public LAB getEstimate() {
        /*var pl = Util.fromLAB(labToEstimate);

        // Translate
        var ps1 = Util.fromLAB(referenceA.getLabSource()).subtract(pl);
        var ps2 = Util.fromLAB(referenceB.getLabSource()).subtract(pl);*/

        var ps1 = Util.fromLAB(referenceA.getLabSource());
        var ps2 = Util.fromLAB(referenceB.getLabSource());
        var straightSource = Straight.betweenPoints(ps1, ps2);
        var quantifier = straightSource.stretchTo(Util.fromLAB(labToEstimate));

        var pt1 = Util.fromLAB(referenceA.getLabTarget());
        var pt2 = Util.fromLAB(referenceB.getLabTarget());
        var straightTarget = Straight.betweenPoints(pt1, pt2);
        var closestPoint = straightTarget.getPoint(quantifier);

        return new LAB(closestPoint.getX(), closestPoint.getY(), closestPoint.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        ReferencePair referencePair = (ReferencePair) o;

        return
            Objects.equals(referenceA, referencePair.referenceA) &&
            Objects.equals(referenceB, referencePair.referenceB);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referenceA, referenceB);
    }
}
