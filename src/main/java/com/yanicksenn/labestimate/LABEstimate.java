package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LABEstimate {
    private final List<Reference> references;

    public LABEstimate(File referenceFile) throws IOException {
        Objects.requireNonNull(referenceFile);

        references = new ArrayList<>();

        try (var br = new BufferedReader(new FileReader(referenceFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                var reference = Reference.ofString(line);
                references.add(reference);
            }
        }

        if (references.isEmpty())
            throw new IllegalArgumentException("references must not be empty");
    }

    public void estimate(LAB inputLAB, StandardIlluminant standardIlluminant) {
        var inputXYZ = inputLAB.toXYZ(standardIlluminant);

        Reference bestReference = null;
        XYZ bestXYZ = null;

        for (var currentReference : references) {
            if (bestReference == null) {
                // TODO Remove two references
                bestReference = currentReference;
                bestXYZ = bestReference.getLabSource().toXYZ(standardIlluminant);
                continue;
            }

            var currentXYZ = currentReference.getLabSource().toXYZ(standardIlluminant);
            var currentDistance = currentXYZ.distanceTo(inputXYZ);
            var bestDistance = bestXYZ.distanceTo(inputXYZ);

            if (currentDistance > bestDistance)
                continue;

            // TODO Remove two references
            bestReference = currentReference;
            bestXYZ = bestReference.getLabSource().toXYZ(standardIlluminant);
        }

        System.out.println("");

        System.out.println("Input:");
        System.out.println(" > LAB: " + inputLAB);
        System.out.println(" > Reference White: " + standardIlluminant);
        System.out.println("");

        System.out.println(" > Source:");
        System.out.println("    > LAB: " + bestReference.getLabSource().toString());
        System.out.println("    > XYZ: " + bestReference.getLabSource().toXYZ(standardIlluminant));
        System.out.println("");

        System.out.println(" > Target:");
        System.out.println("    > LAB: " + bestReference.getLabTarget().toString());
        System.out.println("    > XYZ: " + bestReference.getLabTarget().toXYZ(standardIlluminant));
        System.out.println();

        var deltaE76 = new DeltaE76();

        var delteE76_inputVsReferenceSource = deltaE76.calculate(inputLAB, bestReference.getLabSource());
        System.out.println(" > Delta E76 (Input vs. Reference source)");
        System.out.println("    > " + delteE76_inputVsReferenceSource);
        System.out.println();

        if (delteE76_inputVsReferenceSource > 3.0) {
            System.err.println("    > WARNING: Delta E76 to nearest reference is large");
            System.err.println("    > WARNING: Resulting LAB might be inaccurate");
            System.err.println();
        }

        var deltaE76_referenceSourceVsReferenceTarget = deltaE76.calculate(bestReference.getLabSource(), bestReference.getLabTarget());
        System.out.println(" > Delta E76 (Reference source vs. Reference target)");
        System.out.println("    > " + deltaE76_referenceSourceVsReferenceTarget);
        System.out.println();

        var sourceXYZ = bestReference.getLabSource().toXYZ(standardIlluminant);
        var targetXYZ = bestReference.getLabTarget().toXYZ(standardIlluminant);

        var ox = targetXYZ.getX() - sourceXYZ.getX();
        var oy = targetXYZ.getY() - sourceXYZ.getY();
        var oz = targetXYZ.getZ() - sourceXYZ.getZ();

        var outputXYZ = new XYZ(
            inputXYZ.getX() + ox,
            inputXYZ.getY() + oy,
            inputXYZ.getZ() + oz);

        var outputLAB = outputXYZ.toLAB(standardIlluminant);
        System.out.println("Output:");
        System.out.println(" > LAB: " + outputLAB);
        System.out.println();

        var deltaE76_inputVsOutput = deltaE76.calculate(inputLAB, outputLAB);
        System.out.println(" > Estimated Delta E76");
        System.out.println("    > " + Math.abs(deltaE76_inputVsOutput - deltaE76_referenceSourceVsReferenceTarget));
        System.out.println();
    }
}
