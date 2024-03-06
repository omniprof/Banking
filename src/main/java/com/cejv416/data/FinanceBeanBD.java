package com.cejv416.data;

import java.math.BigDecimal;

public class FinanceBeanBD {

    private BigDecimal pmt;
    private BigDecimal pv;
    private BigDecimal rate;
    private BigDecimal n;
    private BigDecimal fv;
    
    public FinanceBeanBD() {
        pmt = BigDecimal.ZERO;
        pv = BigDecimal.ZERO;
        rate = BigDecimal.ZERO;
        n = BigDecimal.ZERO;
        fv = BigDecimal.ZERO;
    }

    public BigDecimal getPmt() {
        return pmt;
    }

    public void setPmt(BigDecimal pmt) {
        this.pmt = pmt;
    }

    public BigDecimal getPv() {
        return pv;
    }

    public void setPv(BigDecimal pv) {
        this.pv = pv;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public BigDecimal getFv() {
        return fv;
    }

    public void setFv(BigDecimal fv) {
        this.fv = fv;
    }
}
