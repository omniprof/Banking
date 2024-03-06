package com.cejv416.presentation;

import com.cejv416.business.FinanceCalculatorBD;
import com.cejv416.data.FinanceBeanBD;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * This class contains the user interface code. The menu is run in a loop until
 * the user chooses to exit
 *
 * @author
 */
public class FinanceUserInterfaceBD {

    private final FinanceCalculatorBD financeCalculator;
    private final FinanceBeanBD financeBean;
    private final Scanner sc;
    private final NumberFormat numberFormat;

    public FinanceUserInterfaceBD(FinanceBeanBD financeBean, FinanceCalculatorBD financeCalculator) {
        this.financeBean = financeBean;
        this.financeCalculator = financeCalculator;
        sc = new Scanner(System.in);
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    /**
     * Program begins here
     */
    public void perform() {
        
        System.out.println(">>> Using BigDecimal <<<\n");
        
        char choice;

        do {
            choice = menu();
            switch (choice) {
                case 'A' -> { // Ask for loan info
                    requestLoanPaymentInfo();
                    financeCalculator.calculateLoanPayment();
                    displayLoanResult();
                }
                case 'B' -> { // Ask for Savings Goal info
                    requestSavingsGoalInfo();
                    financeCalculator.calculateSavingsGoal();
                    displaySavingsGoalResult();
                }
                case 'C' -> { // Ask for Futire Value info
                    requestFutureValueInfo();
                    financeCalculator.calculateFutureValue();
                    displayFutureValueResult();
                }
                case 'D' -> { // Program exit
                    System.out.println("\nThank you for using the Finance Calculator.");
                    return;
                }
                default ->
                    System.out.println("I should never see this");
            }
        } while (choice != 'D');
    }

    /**
     * Show the menu
     */
    private void displayMenuText() {
        System.out.println("\nPlease enter the letter for one of the following:");

        System.out.println("A. Loan Payment");
        System.out.println("B. Savings Goal");
        System.out.println("C. Future Value");
        System.out.println("D. Exit");
    }

    /**
     * Routine to display a menu that accepts choices A to D
     *
     * @return the menu choice
     */
    private char menu() {

        char choice = 'z';

        do {
            displayMenuText();
            if (sc.hasNext("[a-dA-D]")) {
                choice = sc.next().toUpperCase().charAt(0);
            } else {
                choice = 'z';
            }
            sc.nextLine();
        } while (choice == 'z');
        return choice;
    }

    /* 
    The following three methods will ask the user for the three values 
    necessary for the calculation and store the user input in the FinanceBeanBD 
     */
    private void requestLoanPaymentInfo() {
        financeBean.setPv(inputBigDecimalValue("Enter Present Value ", new BigDecimal("100.0"), new BigDecimal("10000.0")));
        requestRate();
        financeBean.setN(inputBigDecimalValue("Enter Term in Months ", new BigDecimal("12.0"), new BigDecimal("120.0")));
    }

    private void requestSavingsGoalInfo() {
        financeBean.setFv(inputBigDecimalValue("Enter Future Value ", new BigDecimal("1000.0"), new BigDecimal("100000.0")));
        requestRate();
        financeBean.setN(inputBigDecimalValue("Enter Term in Years ", new BigDecimal("1.0"), new BigDecimal("10.0")));
    }

    private void requestFutureValueInfo() {
        financeBean.setPmt(inputBigDecimalValue("Enter Monthly Savings Value ", new BigDecimal("25.0"), new BigDecimal("1000.0")));
        requestRate();
        financeBean.setN(inputBigDecimalValue("Enter Term in Years ", new BigDecimal("1.0"), new BigDecimal("10.0")));
    }

    private void requestRate() {
        financeBean.setRate(inputBigDecimalValue("Enter Interest Rate (0.05 -> 5%) ", new BigDecimal("0.0025"), new BigDecimal("0.2")));
    }

    /*
    The following three methods will display the input and result of each 
    calculation as found in the FinanceBeanBD
     */
    private void displayLoanResult() {
        System.out.println("If you borrow " + numberFormat.format(financeBean.getPv())
                + " the monthly payment will be " + numberFormat.format(financeBean.getPmt())
                + " for " + financeBean.getN() + " months.");
    }

    private void displaySavingsGoalResult() {
        System.out.println("To reach " + numberFormat.format(financeBean.getFv())
                + " you must save " + numberFormat.format(financeBean.getPmt())
                + " every month for " + financeBean.getN() + " years.");
    }

    private void displayFutureValueResult() {
        System.out.println("If you save " + numberFormat.format(financeBean.getPmt())
                + " for " + financeBean.getN().multiply(new BigDecimal("12")) + " months you will save "
                + numberFormat.format(financeBean.getFv()));
    }

    /**
     * This method is used for all input. It needs the prompt, a range of
     * acceptable values, and returns what the user entered
     *
     * @param prompt
     * @return a valid double
     */
    private BigDecimal inputBigDecimalValue(String prompt, BigDecimal min, BigDecimal max) {
        BigDecimal amount = BigDecimal.ZERO;
        boolean validInput = false;
        do {
            System.out.println(prompt);
            if (sc.hasNextBigDecimal()) {
                amount = sc.nextBigDecimal();
                if (amount.compareTo(min) >= 0 && amount.compareTo(max) <= 0) {
                    validInput = true;
                } else {
                    System.out.println("Input out of the range from " + min + " to " + max);
                }
            } else {
                System.out.println("Invalid input.");
            }
            sc.nextLine();
        } while (!validInput);

        return amount;
    }
}
