package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.*;

import java.io.*;
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

    public void estimate(EstimateParams params) {
        Objects.requireNonNull(params, "params must not be null");
        var inputLAB = Objects.requireNonNull(params.getLab(), "lab must not be null");
        var standardIlluminant = Objects.requireNonNull(params.getStandardIlluminant(), "standardIlluminant must not be null");

        var inputXYZ = inputLAB.toXYZ(standardIlluminant);
        var bestReference = (Reference) null;
        var bestXYZ = (XYZ) null;

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

        var verbose = params.isVerbose();
        var printer = new Printer(System.out, verbose);

        printer.verboseln();
        printer.verboseln("Input:");
        printer.verboseln(" > LAB: " + inputLAB);
        printer.verboseln(" > Reference White: " + standardIlluminant);
        printer.verboseln();

        printer.verboseln(" > Source:");
        printer.verboseln("    > LAB: " + bestReference.getLabSource().toString());
        printer.verboseln("    > XYZ: " + bestReference.getLabSource().toXYZ(standardIlluminant));
        printer.verboseln();

        printer.verboseln(" > Target:");
        printer.verboseln("    > LAB: " + bestReference.getLabTarget().toString());
        printer.verboseln("    > XYZ: " + bestReference.getLabTarget().toXYZ(standardIlluminant));
        printer.verboseln();

        var deltaE76 = new DeltaE76();

        var delteE76_inputVsReferenceSource = deltaE76.calculate(inputLAB, bestReference.getLabSource());
        printer.verboseln(" > Delta E76 (Input vs. Reference source)");
        printer.verboseln("    > " + delteE76_inputVsReferenceSource);
        printer.verboseln();

        if (delteE76_inputVsReferenceSource > 3.0) {
            printer.verboseln("    > WARNING: Delta E76 to nearest reference is large");
            printer.verboseln("    > WARNING: Resulting LAB might be inaccurate");
            printer.verboseln();
        }

        var deltaE76_referenceSourceVsReferenceTarget = deltaE76.calculate(bestReference.getLabSource(), bestReference.getLabTarget());
        printer.verboseln(" > Delta E76 (Reference source vs. Reference target)");
        printer.verboseln("    > " + deltaE76_referenceSourceVsReferenceTarget);
        printer.verboseln();

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
        var deltaE76_inputVsOutput = deltaE76.calculate(inputLAB, outputLAB);
        printer.verboseln(" > Estimated Delta E76");
        printer.verboseln("    > " + Math.abs(deltaE76_inputVsOutput - deltaE76_referenceSourceVsReferenceTarget));
        printer.verboseln();

        printer.verboseln(" > Estimated LAB");
        printer.println(outputLAB.toString());
        printer.println();
    }

    private class Printer {
        private final PrintStream source;
        private final boolean verbose;

        public Printer(PrintStream source, boolean verbose) {
            this.source = source;
            this.verbose = verbose;
        }

        public void verboseln(String string) {
            if (verbose)
                this.source.println(string);
        }

        public void verboseln() {
            if (verbose)
                this.source.println();
        }

        public void println(String string) {
            this.source.println(string);
        }

        public void println() {
            this.source.println();
        }
    }
}
