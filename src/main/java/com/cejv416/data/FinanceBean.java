package com.cejv416.data;

/**
 * This version of the FinanceBean uses doubles. Once you can get the correct
 * results with doubles then you can switch to BigDecimal objects
 *
 * @author
 */
public class FinanceBean {

    private double pmt;
    private double pv;
    private double rate;
    private double n;
    private double fv;

    public double getPmt() {
        return pmt;
    }

    public void setPmt(double pmt) {
        this.pmt = pmt;
    }

    public double getPv() {
        return pv;
    }

    public void setPv(double pv) {
        this.pv = pv;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getN() {
        return n;
    }

    public void setN(double n) {
        this.n = n;
    }

    public double getFv() {
        return fv;
    }

    public void setFv(double fv) {
        this.fv = fv;
    }

}
