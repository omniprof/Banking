package com.cejv416.presentation;

import com.cejv416.business.FinanceCalculator;
import com.cejv416.data.FinanceBean;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * This class contains the user interface code. The menu is run in a loop until
 * the user chooses to exit
 *
 * @author
 */
public class FinanceUserInterface {

    private final FinanceCalculator financeCalculator;
    private final FinanceBean financeBean;
    private final Scanner sc;
    private final NumberFormat numberFormat;

    public FinanceUserInterface(FinanceBean financeBean, FinanceCalculator financeCalculator) {
        this.financeBean = financeBean;
        this.financeCalculator = financeCalculator;
        sc = new Scanner(System.in);
        numberFormat = NumberFormat.getCurrencyInstance();
    }

    /**
     * Program begins here
     */
    public void perform() {
        
        System.out.println(">>> Using double <<<\n");
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
                case 'D' -> { // Ask for Compound Interest info
                    requestCompoundInterestInfo();
                    financeCalculator.calculateCompoundInterest();
                    displayCompoundInterestResult();
                }
                case 'E' -> { // Program exit
                    System.out.println("\nThank you for using the Finance Calculator.");
                    return;
                }
                default ->
                    System.out.println("I should never see this");
            }
        } while (choice != 'E');
    }

    /**
     * Show the menu
     */
    private void displayMenuText() {
        System.out.println("\nPlease enter the letter for one of the following:");

        System.out.println("A. Loan Payment");
        System.out.println("B. Savings Goal");
        System.out.println("C. Future Value");
        System.out.println("D. Compound Interest");
        System.out.println("E. Exit");
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
            if (sc.hasNext("[a-eA-E]")) {
                choice = sc.next().toUpperCase().charAt(0);
            } else {
                choice = 'z';
            }
            sc.nextLine();
        } while (choice == 'z');
        return choice;
    }

    private void requestRate() {
        financeBean.setRate(inputDoubleValue("Enter Interest Rate (0.05 -> 5%) ", 0.0025, 0.2));
    }
    /* 
    The following three methods will ask the user for the three values 
    necessary for the calculation and store the user input in the FinanceBean 
     */
    private void requestLoanPaymentInfo() {

        financeBean.setPv(inputDoubleValue("Enter Present Value ", 100.0, 10000.0));
        requestRate();
        financeBean.setN(inputDoubleValue("Enter Term in Months ", 12, 120));
    }

    private void requestSavingsGoalInfo() {
        financeBean.setFv(inputDoubleValue("Enter Future Value ", 1000.0, 100000.0));
        requestRate();
        financeBean.setN(inputDoubleValue("Enter Term in Years ", 1, 10));
    }

    private void requestFutureValueInfo() {
        financeBean.setPmt(inputDoubleValue("Enter Monthly Savings Value ", 25, 1000));
        requestRate();
        financeBean.setN(inputDoubleValue("Enter Term in Years ", 1, 10));
    }

    private void requestCompoundInterestInfo() {
        financeBean.setPv(inputDoubleValue("Enter Present Value ", 25, 1000));
        requestRate();
        financeBean.setN(inputDoubleValue("Enter Term in Years ", 1, 10));
    }

    /*
    The following three methods will display the input and result of each 
    calculation as found in the FinanceBean
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
                + " for " + financeBean.getN() + " months you will save "
                + numberFormat.format(financeBean.getFv()));
    }

    private void displayCompoundInterestResult() {
        System.out.println("If you deposit " + numberFormat.format(financeBean.getPv())
                + " for " + financeBean.getN() + " years at " + financeBean.getRate() 
                + " annual interest rate you will have " 
                + numberFormat.format(financeBean.getFv()));
    }

    /**
     * This method is used for all input. It needs the prompt, a range of
     * acceptable values, and returns what the user entered
     *
     * @param prompt
     * @return a valid double
     */
    private double inputDoubleValue(String prompt, double min, double max) {
        double amount = 0.0;
        boolean invalidInput = true;
        do {
            System.out.println(prompt);
            if (sc.hasNextDouble()) {
                amount = sc.nextDouble();
                if (amount >= min && amount <= max) {
                    invalidInput = false;
                } else {
                    System.out.println("Input out of the range from " + min + " to " + max);
                }
            } else {
                System.out.println("Invalid input.");
            }
            sc.nextLine();
        } while (invalidInput == true);

        return amount;
    }
}
