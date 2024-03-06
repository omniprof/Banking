package com.cejv416.business;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import com.cejv416.data.FinanceBeanBD;

public class FinanceCalculatorBD {

    private final FinanceBeanBD financeBean;

    public FinanceCalculatorBD(FinanceBeanBD financeBean) {
        this.financeBean = financeBean;
    }

    /**
     * Payments on a loan
     *
     * @throws ArithmeticException
     */
    public void calculateLoanPayment() throws ArithmeticException {
        // Convert APR to monthly
        BigDecimal adjustedRate = financeBean.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        BigDecimal temp;
        // (1+rate)
        temp = BigDecimal.ONE.add(adjustedRate);

        // (1+rate)^term
        temp = temp.pow(financeBean.getN().intValueExact());

        // BigDecimal pow does not support negative exponents so divide 1 by the result
        temp = BigDecimal.ONE.divide(temp, MathContext.DECIMAL64);

        // 1 - (1+rate)^-term
        temp = BigDecimal.ONE.subtract(temp);

        // rate / (1 - (1+rate)^-term)
        temp = adjustedRate.divide(temp, MathContext.DECIMAL64);

        // pv * (rate / 1 - (1+rate)^-term)
        temp = financeBean.getPv().multiply(temp);
        
        // Round to two decimals
        temp = temp.setScale(2, RoundingMode.HALF_EVEN);
        financeBean.setPmt(temp.abs());
    }

    /**
     * Future value of financeBean invested at regular intervals
     *
     * @throws ArithmeticException
     */
    public void calculateFutureValue() throws ArithmeticException {
        // Convert APR to monthly
        BigDecimal adjustedRate = financeBean.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        // Multiply N by 12
        BigDecimal adjustedN = financeBean.getN().multiply(new BigDecimal("12"));

        BigDecimal temp;
        // (1+rate)
        temp = BigDecimal.ONE.add(adjustedRate);
        // (1+rate)^term
        temp = temp.pow(adjustedN.intValueExact());
        // 1 - (1+rate)^term
        temp = BigDecimal.ONE.subtract(temp);
        // (1 - (1+rate)^-term) / rate
        temp = temp.divide(adjustedRate, MathContext.DECIMAL64);
        // pmt * ((1 - (1+rate)^-term) / rate)
        temp = financeBean.getPmt().multiply(temp);
        temp = temp.setScale(2, RoundingMode.HALF_EVEN);
        financeBean.setFv(temp.abs());
    }

    /**
     * How much to set aside at regular intervals to reach a specific goal
     *
     * @throws ArithmeticException
     */
    public void calculateSavingsGoal() throws ArithmeticException {
        // Convert APR to monthly
        BigDecimal adjustedRate = financeBean.getRate().divide(new BigDecimal("12"), MathContext.DECIMAL64);

        // Multiply N by 12
        BigDecimal adjustedN = financeBean.getN().multiply(new BigDecimal("12"));

        BigDecimal temp;
        // (1+rate)
        temp = BigDecimal.ONE.add(adjustedRate);
        // (1+rate)^term
        temp = temp.pow(adjustedN.intValueExact());
        // 1 - ((1+rate)^term)
        temp = BigDecimal.ONE.subtract(temp);
        // rate / (1 - (1+rate)^term)
        temp = adjustedRate.divide(temp, MathContext.DECIMAL64);
        // fv * (rate / (1 - (1+rate)^term))
        temp = financeBean.getFv().multiply(temp);
        temp = temp.setScale(2, RoundingMode.HALF_EVEN);
        financeBean.setPmt(temp.abs());
    }
}
