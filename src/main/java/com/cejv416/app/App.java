package com.cejv416.app;

import com.cejv416.presentation.FinanceUserInterface;
import com.cejv416.business.FinanceCalculator;
import com.cejv416.data.FinanceBean;
import java.io.IOException;

/**
 * This class is complete
 *
 * @author
 */
public class App {

    // All classes 
    private FinanceBean financeBean;
    private FinanceCalculator financeCalculator;
    private FinanceUserInterface financeUserInterface;

    /**
     * This method will create an instance of each class. The FinanceCalculator
     * needs the FinanceBean while the FinanceUserInterface requires the
     * FinanceBean and the FinanceCalculator
     */
    public void perform() {
        financeBean = new FinanceBean();
        financeCalculator = new FinanceCalculator(financeBean);
        financeUserInterface = new FinanceUserInterface(financeBean, financeCalculator);

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
        new App().perform();
        System.out.println("Press Enter to continue");
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

}
