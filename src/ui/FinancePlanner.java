package ui;

import Budget_stuff.AccountControl;
import Budget_stuff.BeginnerFinance;
import Budget_stuff.ExpertFinance;
import Budget_stuff.FinancePlan;
import exceptions.NegativeNumberException;

import java.util.Scanner;
import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);


    public FinancePlanner() throws IOException, NegativeNumberException {
        AccountControl currentUser = new AccountControl("", 0);

        int financeType;
        String name;

        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");


        Float balance = Float.parseFloat(lines.get(0));

        while (true) {
            //AccountControl currentUser = new AccountControl();

            currentUser.stateName();
            name = scanner.nextLine();
            currentUser.establishUser(name);
            currentUser.addUser(name);


            while (true) {
                try {
                    System.out.println("Choose a type (0)Beginner (1)Expert");
                    financeType = scanner.nextInt();
                    break;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Please try again.");
                    scanner.nextLine();
                }
            }

            if (financeType == 0) {
                currentUser.retID().put(currentUser, "Beginner");
                currentUser.addType(financeType);
                FinancePlan beginnerFinance_plan = new BeginnerFinance();
                beginnerFinance_plan.addBalance(balance);
                financeAction(beginnerFinance_plan);
                writer.println(Float.toString(beginnerFinance_plan.retBalance()));
                writer.close();
            } else if (financeType == 1) {
                currentUser.retID().put(currentUser, "Expert");
                currentUser.addType(financeType);
                FinancePlan expertFinance_plan = new ExpertFinance();
                expertFinance_plan.addBalance(balance);
                financeAction(expertFinance_plan);
                writer.println(Float.toString(expertFinance_plan.retBalance()));
                writer.close();
            }

            System.out.println("Accounts so far:");
            System.out.println(currentUser.displayID());

            System.out.println("try again? (0)Yes (1)No (2) Remove name");
            int tryAgain = scanner.nextInt();
            if (tryAgain == 1) { break; }
            else if (tryAgain == 0) {
                currentUser.stateName();
                name = scanner.nextLine();
                currentUser.establishUser(name);
                currentUser.addUser(name);
            }
            else if (tryAgain == 2) {
                currentUser.retID().remove(currentUser);
            }

        }


//        beginnerFinance_plan.shutdown();
//        beginnerFinance_plan.fin();

        System.out.println("goodbye!");



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
