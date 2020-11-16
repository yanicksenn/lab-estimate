package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;

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

    public ReferencePair estimate(LAB labToEstimate) {

        var referenceCandidates = new ReferenceCandidates(labToEstimate, 10);
        for (var reference : references) {
            referenceCandidates.addCandidate(reference);
        }

        ReferencePair bestReferencePair = null;
        for (var referenceA : referenceCandidates) {
            for (var referenceB : referenceCandidates) {
                if (referenceA.equals(referenceB)) {
                    continue;
                }

                if (bestReferencePair == null) {
                    bestReferencePair = new ReferencePair(labToEstimate, referenceA, referenceB);
                    continue;
                }

                var currentReferencePair = new ReferencePair(labToEstimate, referenceA, referenceB);
                if (currentReferencePair.getError() >= bestReferencePair.getError()) {
                    continue;
                }

                bestReferencePair = currentReferencePair;
            }
        }

        return bestReferencePair;
    }
}
