package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;

import java.util.*;

public class ReferenceCandidates implements Iterable<Reference> {
    private final LAB labToEstimate;
    private final int size;

    private final List<Reference> candidates;
    private final Comparator<Reference> comparator;

    public ReferenceCandidates(LAB labToEstimate, int size) {
        Objects.requireNonNull(labToEstimate);

        if (size < 1)
            throw new IllegalArgumentException("size must be greater than zero");

        this.labToEstimate = labToEstimate;
        this.size = size;
        this.candidates = new ArrayList<>();
        this.comparator = Comparator.comparing((r) -> r.getLabSource().distanceTo(labToEstimate));
    }

    public void addCandidate(Reference newCandidate) {
        Objects.requireNonNull(newCandidate);

        candidates.add(newCandidate);
        candidates.sort(comparator);
        candidates.removeIf((r) -> candidates.indexOf(r) >= size);
    }

    public List<Reference> getCandidates() {
        return Collections.unmodifiableList(candidates);
    }

    @Override
    public Iterator<Reference> iterator() {
        return getCandidates().iterator();
    }
}
