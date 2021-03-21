package com.yanicksenn.labestimate;

import com.yanicksenn.labestimate.color.LAB;
import com.yanicksenn.labestimate.color.StandardIlluminant;

public class EstimateParams {
    private LAB lab;
    private StandardIlluminant standardIlluminant;
    private boolean verbose;

    public EstimateParams(LAB lab, StandardIlluminant standardIlluminant, boolean verbose) {
        setLab(lab);
        setStandardIlluminant(standardIlluminant);
        setVerbose(verbose);
    }

    public EstimateParams(LAB lab, StandardIlluminant standardIlluminant) {
        this(lab, standardIlluminant, false);
    }

    public LAB getLab() {
        return lab;
    }

    public void setLab(LAB lab) {
        this.lab = lab;
    }

    public StandardIlluminant getStandardIlluminant() {
        return standardIlluminant;
    }

    public void setStandardIlluminant(StandardIlluminant standardIlluminant) {
        this.standardIlluminant = standardIlluminant;
    }

    public boolean isVerbose() {
        return verbose;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }
}
