package ui;

import Budget_stuff.BeginnerFinance;
import Budget_stuff.ExpertFinance;
import Budget_stuff.FinancePlan;

import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);


    public FinancePlanner() throws IOException {
        FinancePlan beginnerFinance_plan = new BeginnerFinance();
        FinancePlan expertFinance_plan = new ExpertFinance();

        int action;
        int financeType;

        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");


        Float balance = Float.parseFloat(lines.get(0));
        beginnerFinance_plan.addBalance(balance);
        expertFinance_plan.addBalance(balance);

        beginnerFinance_plan.financeChooseType();
        financeType = scanner.nextInt();

        if (financeType == 0) {
            beginnerFinance_plan.beginBudget();
            beginnerFinance_plan.fromZero();
            while (true) {
                float spending;
                float amount;
                beginnerFinance_plan.enterStartPlan();
                action = scanner.nextInt();

                if (action == 1) {
                    System.out.println("Enter amount of money to your balance");
                    amount = scanner.nextFloat();

                    if (amount >= 100) {
                        beginnerFinance_plan.addBalance(amount);
                        beginnerFinance_plan.compliment();

                    } else if (amount < 0) {
                            beginnerFinance_plan.rip();
                            break;
                        } else {
                            beginnerFinance_plan.addBalance(amount);
                        }
                    } else if (action == 2) {
                        System.out.println("Enter amount of spending");
                        spending = scanner.nextFloat();

                        if (spending > beginnerFinance_plan.retBalance()) {
                            beginnerFinance_plan.rip();
                            break;
                        } else {
                            beginnerFinance_plan.subSpending(spending);
                            System.out.println("Balance is now: " + beginnerFinance_plan.retBalance());
                        }
                    } else if (action == 3) {
                        break;
                    }
                }
            }

            else if (financeType == 1) {
                expertFinance_plan.beginBudget();
                expertFinance_plan.fromZero();
                while (true) {
                    float spending;
                    float amount;
                    expertFinance_plan.enterStartPlan();
                    action = scanner.nextInt();

                    if (action == 1) {
                        System.out.println("Enter amount of money to your balance");
                        amount = scanner.nextFloat();

                    if (amount >= 1000) {
                        expertFinance_plan.addBalance(amount);
                        expertFinance_plan.compliment();
                    } else if (amount < 0) {
                        expertFinance_plan.rip();
                        break;
                    } else {
                        expertFinance_plan.addBalance(amount);
                    }
                } else if (action == 2) {
                    System.out.println("Enter amount of spending");
                    spending = scanner.nextFloat();

                    if (spending > beginnerFinance_plan.retBalance()) {
                        expertFinance_plan.rip();
                        break;
                    } else {
                        expertFinance_plan.subSpending(spending);
                        System.out.println("Balance is now: " + beginnerFinance_plan.retBalance());
                    }
                } else if (action == 3) {
                    break;
                }
            }
        }


        beginnerFinance_plan.shutdown();
        beginnerFinance_plan.fin();

        writer.println(Float.toString(beginnerFinance_plan.retBalance()));
        writer.close();
        return;


    }

    public static void main(String[] args) throws IOException {
        new FinancePlanner();
    }

}
