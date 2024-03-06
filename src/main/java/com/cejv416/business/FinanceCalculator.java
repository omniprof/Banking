package com.cejv416.business;

import com.cejv416.data.FinanceBean;

/**
 * Here you will have to perform all the calculations using the values in the
 * bean
 *
 * @author
 */
public class FinanceCalculator {

    private final FinanceBean financeBean;

    /**
     * Constructor that receive the shared FinanceBeanBD
     *
     * @param financeBean
     */
    public FinanceCalculator(FinanceBean financeBean) {
        this.financeBean = financeBean;
    }

    /**
     * Calculate loan
     */
    public void calculateLoanPayment() {
        double monthlyPercentageRate = financeBean.getRate() / 12.0;
        double divisor = 1 - Math.pow(1 + monthlyPercentageRate, -financeBean.getN());
        financeBean.setPmt(financeBean.getPv() * monthlyPercentageRate / divisor);
    }

    /**
     * Calculate Savings Goal. Input for term is in years so multiply it by 12
     */
    public void calculateSavingsGoal() {
        double monthlyPercentageRate = financeBean.getRate() / 12.0;
        double termInMonths = financeBean.getN() * 12.0;
        double divisor = 1 - Math.pow(1 + monthlyPercentageRate, termInMonths);
        financeBean.setPmt(Math.abs(financeBean.getFv() * monthlyPercentageRate / divisor));
    }

    /**
     * Calculate Future Value. Input for term is in years so multiply it by 12
     */
    public void calculateFutureValue() {
        double monthlyPercentageRate = financeBean.getRate() / 12.0;
        double termInMonths = financeBean.getN() * 12.0;
        double dividend = 1 - Math.pow(1 + monthlyPercentageRate, termInMonths);
        financeBean.setFv(Math.abs(financeBean.getPmt() * dividend / monthlyPercentageRate));
    }

    public void calculateCompoundInterest() {
        financeBean.setFv(financeBean.getPv() * Math.pow(1 + financeBean.getRate() / 12.0, financeBean.getN() * 12.0));
    }
}
