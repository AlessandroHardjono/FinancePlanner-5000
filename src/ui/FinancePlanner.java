package ui;

import Budget_stuff.FinancePlan;
//import Budget_stuff.MathyMath;
import java.util.Scanner;



public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);

    public FinancePlanner() {
        FinancePlan finance_plan = new FinancePlan();
        finance_plan.beginBudget();
        int action = -1;

        while (true) {
            float amount = 0;
            float spending = 0;
            finance_plan.enterStartPlan();
            action = scanner.nextInt();

            if (action == 1) {
                System.out.println("Enter amount of money to your balance");
                amount = scanner.nextFloat();

                if (amount >= 1000) {
                    finance_plan.compliment();
                } else if (amount <= 0) {
                    finance_plan.rip();
                    break;
                }
            } else if (action == 2) {
                System.out.println("Enter amount of spending");
                spending = scanner.nextFloat();

                if (spending > amount) {
                    finance_plan.rip();
                    break;
                } else {
                    amount -= spending;
                    System.out.println("Balance is now: " + amount);
                }
            } else if (action == 3) {
                break;
            }
        }
        System.out.println("FinancePlanner Shutdown");
        finance_plan.fin();
        return;


    }

    public static void main(String[] args) {
        new FinancePlanner();
    }
}
