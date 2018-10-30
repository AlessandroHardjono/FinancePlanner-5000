package ui;

import Budget_stuff.AccountControl;
import Budget_stuff.BeginnerFinance;
import Budget_stuff.ExpertFinance;
import Budget_stuff.FinancePlan;
import exceptions.NegativeNumberException;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);
    HashMap<AccountControl, FinancePlan> identifyType;


    public FinancePlanner() throws IOException, NegativeNumberException {
        identifyType = new HashMap<>();
        FinancePlan beginnerFinance_plan = new BeginnerFinance();
        FinancePlan expertFinance_plan = new ExpertFinance();
        AccountControl currentUser = new AccountControl();

        int financeType;
        String name;

        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");

        currentUser.stateName();

        while (true) {
            try {
                name = scanner.nextLine();
                currentUser.establishUser(name);
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please try again.");
                scanner.nextLine();
            }
        }




        Float balance = Float.parseFloat(lines.get(0));
        beginnerFinance_plan.addBalance(balance);
        expertFinance_plan.addBalance(balance);
        beginnerFinance_plan.financeChooseType();

        while (true) {
            try {
                financeType = scanner.nextInt();
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please try again.");
                scanner.nextLine();
            }
        }

        if (financeType == 0) {
            financeAction(beginnerFinance_plan);
        } else if (financeType == 1) {
            financeAction(expertFinance_plan);
        }


        beginnerFinance_plan.shutdown();
        beginnerFinance_plan.fin();

        writer.println(Float.toString(beginnerFinance_plan.retBalance()));
        writer.close();
        return;


    }

    private void financeAction(FinancePlan f) throws NegativeNumberException{
        int action;
        f.beginBudget();
        f.fromZero();
        while (true) {
            float spending;
            float amount;
            f.enterStartPlan();
            while (true) {
                try {
                    action = scanner.nextInt();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please try again.");
                    scanner.nextLine();
                } finally {
                    System.out.println("Input a number.");
                }
            }

            if (action == 1) {
                System.out.println("Enter amount of money to your balance");
                while (true) {
                    try {
                        amount = scanner.nextFloat();
                        break;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please try again.");
                        scanner.nextLine();
                    }
                }
                if (amount >= 100) {
                    f.addBalance(amount);
                    f.compliment();
                } else if (amount < 0) {
                    f.rip();
                } else {
                    f.addBalance(amount);
                }

            } else if (action == 2) {
                System.out.println("Enter amount of spending");
                while (true) {
                    try {
                        spending = scanner.nextFloat();
                        break;
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please try again.");
                        scanner.nextLine();
                    }
                }

                if (spending > f.retBalance()) {
                    throw new NegativeNumberException("Game Over.");
                } else {
                    f.subSpending(spending);
                    System.out.println("Balance is now: " + f.retBalance());
                }
            } else if (action == 3) {
                break;
            }
        }
    }



    public static void main(String[] args) throws IOException, NegativeNumberException {
        new FinancePlanner();
    }

}
