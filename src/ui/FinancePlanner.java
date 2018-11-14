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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


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
                beginnerFinanceAction(currentUser, financeType, writer, balance);
            } else if (financeType == 1) {
                expertFinanceAction(currentUser, financeType, writer, balance);
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

    private void expertFinanceAction(AccountControl currentUser, int financeType, PrintWriter writer, Float balance) throws NegativeNumberException, IOException {
        currentUser.retID().put(currentUser, "Expert");
        currentUser.addType(financeType);

        FinancePlan expertFinance_plan = new ExpertFinance();
        expertFinance_plan.addBalance(balance);
        financeAction(expertFinance_plan);
        writer.println(Float.toString(expertFinance_plan.retBalance()));
        writer.close();
    }

    private void beginnerFinanceAction(AccountControl currentUser, int financeType, PrintWriter writer, Float balance) throws NegativeNumberException, IOException {
        currentUser.retID().put(currentUser, "Beginner");
        currentUser.addType(financeType);

        FinancePlan beginnerFinance_plan = new BeginnerFinance();
        currentUser.addObserver(beginnerFinance_plan);

        beginnerFinance_plan.addBalance(balance);
        financeAction(beginnerFinance_plan);
        writer.println(Float.toString(beginnerFinance_plan.retBalance()));
        writer.close();
    }


    private void financeAction(FinancePlan f) throws NegativeNumberException, IOException {
        int action;
        f.beginBudget();
        f.fromZero();
        while (true) {
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
                addBalanceToPlan(f);
            } else if (action == 2) {
                spendBalanceFromPlan(f);
            } else if (action == 3) {
                break;
            } else if (action == 4) {
                displayWeb();
            }
        }


    }

    private void spendBalanceFromPlan(FinancePlan f) throws NegativeNumberException {
        float spending;
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
    }

    private void addBalanceToPlan(FinancePlan f) throws NegativeNumberException {
        float amount;
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
            throw new NegativeNumberException("Game Over.");
        } else {
            f.addBalance(amount);
            System.out.println("Balance is now: " + f.retBalance());
        }
    }

    private void displayWeb() throws IOException {

        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {

            if (br != null) {
                br.close();
            }
        }
    }

    public static void main(String[] args) throws IOException, NegativeNumberException {
        new FinancePlanner();
    }

}
