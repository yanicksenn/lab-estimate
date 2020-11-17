package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;
import com.yanicksenn.labestimate.color.StandardIlluminant;
import com.yanicksenn.labestimate.color.XY;
import com.yanicksenn.labestimate.color.XYZ;

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

    public void estimate(LAB labToEstimate, StandardIlluminant standardIlluminant) {
        var inputXYZ = labToEstimate.toXYZ(standardIlluminant);

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
        System.out.println(" > LAB: " + labToEstimate);
        System.out.println(" > Reference White: " + standardIlluminant);
        System.out.println("");

        System.out.println("Closest reference to the input LAB:");
        System.out.println(" > CMYK: " + bestReference.getCmyk());
        System.out.println("");

        System.out.println(" > Source:");
        System.out.println("    > LAB: " + bestReference.getLabSource().toString());
        System.out.println("    > XYZ: " + bestReference.getLabSource().toXYZ(standardIlluminant));
        System.out.println("");

        System.out.println(" > Target:");
        System.out.println("    > LAB: " + bestReference.getLabTarget().toString());
        System.out.println("    > XYZ: " + bestReference.getLabTarget().toXYZ(standardIlluminant));
        System.out.println("");

        var sourceXYZ = bestReference.getLabSource().toXYZ(standardIlluminant);
        var targetXYZ = bestReference.getLabTarget().toXYZ(standardIlluminant);
        var distance = sourceXYZ.distanceTo(targetXYZ);
        System.out.println("Error:");
        System.out.println(" > distance: " + distance);

        var minXYZ = new XYZ(0, 0, 0);
        var maxXYZ = new XYZ(1, 1, 1);
        var maxDistance = minXYZ.distanceTo(maxXYZ);
        System.out.println(" > percent: " + (distance / maxDistance));

        var ox = targetXYZ.getX() - sourceXYZ.getX();
        var oy = targetXYZ.getY() - sourceXYZ.getY();
        var oz = targetXYZ.getZ() - sourceXYZ.getZ();
        System.out.println(" > Offset X: " + ox);
        System.out.println(" > Offset Y: " + oy);
        System.out.println(" > Offset Z: " + oz);
        System.out.println("");

        var outputXYZ = new XYZ(
            inputXYZ.getX() + ox,
            inputXYZ.getY() + oy,
            inputXYZ.getZ() + oz);

        var outputLAB = outputXYZ.toLAB(standardIlluminant);
        System.out.println("Output:");
        System.out.println(" > LAB: " + outputLAB);
        System.out.println("");
    }
}
