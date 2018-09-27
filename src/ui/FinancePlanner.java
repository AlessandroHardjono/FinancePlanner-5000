package ui;

import Budget_stuff.FinancePlan;
import Budget_stuff.MathyMath;
import java.util.Scanner;


public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);

    public FinancePlanner() {
        FinancePlan finance_plan = new FinancePlan();
        MathyMath mathFinance = new MathyMath();
        finance_plan.beginBudget();
        mathFinance.fromZero();
        int action;

        while (true) {
            float spending;
            float amount;
            finance_plan.enterStartPlan();
            action = scanner.nextInt();

            if (action == 1) {
                System.out.println("Enter amount of money to your balance");
                amount = scanner.nextFloat();

                if (amount >= 1000) {
                    mathFinance.AddBalance(amount);
                    finance_plan.compliment();

                } else if (amount <= 0) {
                    finance_plan.rip();
                    break;
                } else {
                    mathFinance.AddBalance(amount);
                }
            } else if (action == 2) {
                System.out.println("Enter amount of spending");
                spending = scanner.nextFloat();

                if (spending >= mathFinance.retBalance()) {
                    finance_plan.rip();
                    break;
                } else {
                    mathFinance.SubSpending(spending);
                    System.out.println("Balance is now: " + mathFinance.retBalance());
                }
            } else if (action == 3) {
                break;
            }
        }
        finance_plan.shutdown();
        finance_plan.fin();
        return;


    }

    public static void main(String[] args) {
        new FinancePlanner();
    }
}
