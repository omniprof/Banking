package com.cejv416.app;

import com.cejv416.presentation.FinanceUserInterfaceBD;
import com.cejv416.business.FinanceCalculatorBD;
import com.cejv416.data.FinanceBeanBD;

/**
 * This class is complete
 *
 * @author
 */
public class AppBD {

    // All classes 
    private FinanceBeanBD financeBean;
    private FinanceCalculatorBD financeCalculator;
    private FinanceUserInterfaceBD financeUserInterface;
    
    /**
     * This method will create an instance of each class. The FinanceCalculator
     * needs the FinanceBean while the FinanceUserInterface requires the
     * FinanceBean and the FinanceCalculator
     */
    public void perform() {
        financeBean = new FinanceBeanBD();
        financeCalculator = new FinanceCalculatorBD(financeBean);
        financeUserInterface = new FinanceUserInterfaceBD(financeBean, financeCalculator);

        // The loop that runs the program until they choose Exit is in 
        // FinanceUserInterface
        financeUserInterface.perform();
    }

    /**
     * Where it all begins
     *
     * @param args
     */
    public static void main(String... args) {
        new AppBD().perform();
    }

}
