package ui;

import Budget_stuff.AccountControl;
import Budget_stuff.BeginnerFinance;
import Budget_stuff.ExpertFinance;
import Budget_stuff.FinancePlan;
import exceptions.NegativeNumberException;

import java.io.IOException;
import java.util.List;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import java.awt.FlowLayout;
import org.json.JSONObject;


public class FinancePlanner extends JFrame {
    private JPanel nameField;
    private JPanel boeField;
    private JPanel addField;
    private JPanel spendField;

    public FinancePlanner() throws IOException, NegativeNumberException {
        Float balance;

        nameField = new JPanel();
        boeField = new JPanel();
        nameField.setSize(350,350);
        boeField.setSize(350,350);



        boeField.setVisible(true);
        setLayout(new FlowLayout());

        AccountControl currentUser = new AccountControl("", 0);
        int financeType;
        String name;

        List<String> lines = Files.readAllLines(Paths.get("outputFile.txt"));
        PrintWriter writer = new PrintWriter("outputFile.txt", "UTF-8");

        if (lines.size() != 0) {
            balance = Float.parseFloat(lines.get(0));
        } else {
            balance = Float.valueOf(0);
        }

        while (true) {

            currentUser.stateName();
            nameField = new JPanel();
            while (true) {
                try {
                    String nameChosen = JOptionPane.showInputDialog("State your name: ");
                    name = nameChosen;
                    break;
                } catch (java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(nameField, "Enter a name, please.");
                }
            }

            currentUser.establishUser(name);
            currentUser.addUser(name);

            JButton buttonBeginner = new JButton ("Beginner");
            JButton buttonExpert = new JButton("Expert");

            boeField.add(buttonBeginner);
            boeField.add(buttonExpert);

            while (true) {
                try {
                    String choiceF = JOptionPane.showInputDialog(
                            "Choose a type\n" +
                            "(0)Beginner\n" +
                            "(1) Expert");
                    financeType = Integer.parseInt(choiceF);
                    break;
                } catch (java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(boeField, "Enter either (0) for Beginner or" +
                            "(1) for Expert.");
                } catch (java.lang.NumberFormatException e) {
                    JOptionPane.showMessageDialog(boeField, "Enter either (0) for Beginner or" +
                            "(1) for Expert.");
                }
            }

            if (financeType == 0) {
                beginnerFinanceAction(currentUser, financeType, writer, balance);
            } else if (financeType == 1) {
                expertFinanceAction(currentUser, financeType, writer, balance);
            }

            JLabel accounts = new JLabel("Accounts so far: "+ currentUser.displayID());
            accounts.setToolTipText("This is the amount of accounts active right now.");
            nameField.add(accounts);
            nameField.setVisible(true);

            String tryInput = JOptionPane.showInputDialog(
                    "try again?\n" +
                    "(0)Yes\n" +
                    "(1)No\n" +
                    "(2)Remove name");
            int tryAgain = Integer.parseInt(tryInput);

            if (tryAgain == 1) { break; }
            else if (tryAgain == 0) {
                continue;
            }
            else if (tryAgain == 2) {
                currentUser.retID().remove(currentUser);
            }

        }

        JLabel byeBye = new JLabel("goodbye!");
        nameField.add(byeBye);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return;


    }

    private void expertFinanceAction(AccountControl currentUser, int financeType, PrintWriter writer,
                                     Float balance)
            throws NegativeNumberException, IOException {
        currentUser.retID().put(currentUser, "Expert");
        currentUser.addType(financeType);

        FinancePlan expertFinance_plan = new ExpertFinance();
        expertFinance_plan.addBalance(balance);
        financeAction(expertFinance_plan);

        writer.println(Float.toString(expertFinance_plan.retBalance()));
        writer.close();
    }

    private void beginnerFinanceAction(AccountControl currentUser, int financeType, PrintWriter writer,
                                       Float balance)
            throws NegativeNumberException, IOException {
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

            while (true) {
                try {
                    String starter = JOptionPane.showInputDialog(f.enterStartPlan());
                    action = Integer.parseInt(starter);
                    break;
                } catch (java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(boeField, "Enter a the following options please.");
                    continue;
                } catch (java.lang.NumberFormatException e) {
                    JOptionPane.showMessageDialog(boeField, "Enter a the following options please.");
                    continue;
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

        while (true) {
            try {
                String spendMessage = JOptionPane.showInputDialog("Enter amount of spending");
                spending = Float.parseFloat(spendMessage);
                break;
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(spendField, "Please enter a number to spend.");
                continue;
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(spendField, "Please enter a number to spend.");
                continue;
            }
        }

        if (spending > f.retBalance()) {
            throw new NegativeNumberException("Game Over.");
        } else {
            f.subSpending(spending);
            JOptionPane.showMessageDialog(spendField, "Balance is now: €"+f.retBalance());
        }
    }

    private void addBalanceToPlan(FinancePlan f) throws NegativeNumberException {
        float amount;
        while (true) {
            try {
                String addMessage = JOptionPane.showInputDialog("Enter amount to balance:");
                amount = Float.parseFloat(addMessage);
                break;
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(addField, "Please enter a number to spend.");
                continue;
            } catch (java.lang.NumberFormatException e) {
                JOptionPane.showMessageDialog(addField, "Please enter a number to spend.");
                continue;
            }
        }

        if (amount >= 100) {
            f.addBalance(amount);
            JLabel compliment = new JLabel("nice.");
            JOptionPane.showMessageDialog(addField, "Balance is now: €"+f.retBalance());
            compliment.add(nameField);
        } else if (amount < 0) {
            throw new NegativeNumberException("Game Over.");
        } else {
            f.addBalance(amount);
            JOptionPane.showMessageDialog(addField, "Balance is now: €"+f.retBalance());
        }
    }

    private void displayWeb() throws IOException, MalformedURLException {

        BufferedReader br = null;

        try {
            String apiKey = "6aed9371bcf68da6cee41dfebf8de257";
            String financeLink = "http://data.fixer.io/api/latest?access_key=";
            String actualURL = financeLink+apiKey;

            URL url = new URL(actualURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
            JSONObject baseCurrency = new JSONObject(sb.toString());
            JSONObject todayDate = new JSONObject(sb.toString());

            String baseCurrencyString = baseCurrency.getString("base");
            String todayDateString = todayDate.getString("date");

            JLabel timeStampNow = new JLabel("Base currency is: " + baseCurrencyString);

            JOptionPane.showMessageDialog(boeField, "Base currency is: " + baseCurrencyString +
                    "\nToday's date: " + todayDateString);

            add(timeStampNow);

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
