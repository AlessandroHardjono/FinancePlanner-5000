package ui;

import Budget_stuff.FinancePlan;
//import Budget_stuff.MathyMath;


import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FinancePlanner {
    Scanner scanner = new Scanner(System.in);


    public FinancePlanner() throws IOException{
        FinancePlan finance_plan = new FinancePlan();
        finance_plan.beginBudget();
        finance_plan.fromZero();
        int action;

        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        PrintWriter writer = new PrintWriter("outputFile.txt","UTF-8");

        // Assuming lines only has one line, the current balance
        //Float balance = Float.parseFloat(lines.get(0));
        Float balance = Float.parseFloat(lines.get(0));
        finance_plan.addBalance(balance);


        while (true) {
            float spending;
            float amount;
            finance_plan.enterStartPlan();
            action = scanner.nextInt();

            if (action == 1) {
                System.out.println("Enter amount of money to your balance");
                amount = scanner.nextFloat();

                if (amount >= 1000) {
                    finance_plan.addBalance(amount);
                    finance_plan.compliment();

                } else if (amount < 0) {
                    finance_plan.rip();
                    break;
                } else {
                    finance_plan.addBalance(amount);
                }
            } else if (action == 2) {
                System.out.println("Enter amount of spending");
                spending = scanner.nextFloat();

                if (spending > finance_plan.retBalance()) {
                    finance_plan.rip();
                    break;
                } else {
                    finance_plan.subSpending(spending);
                    System.out.println("Balance is now: " + finance_plan.retBalance());
                }
            } else if (action == 3) {
                break;
            }
        }
        finance_plan.shutdown();
        finance_plan.fin();

        writer.println(Float.toString(finance_plan.retBalance()));
        writer.close();
        return;


    }

    public static void main(String[] args) throws IOException {
        new FinancePlanner();
    }

//    @Override
//    public void fromZero() {
//        balance = 0;
//        amount = 0;
//        spending = 0;
//    }
//
//    @Override
//    public void addBalance(float amount) {
//        balance += amount;
//    }
//
//    @Override
//    public void subSpending(float spending) {
//        balance -= spending;
//    }
//
//    @Override
//    public float retBalance() {
//        return balance;
//    }
//
//    @Override
//    public float retAmount() {
//        return amount;
//    }
//
//    @Override
//    public float retSpending() {
//        return spending;
//    }


//    public static ArrayList<String> splitOnSpace(String line){
//        String[] splits = line.split(" ");
//        return new ArrayList<>(Arrays.asList(splits));
//    }
}
