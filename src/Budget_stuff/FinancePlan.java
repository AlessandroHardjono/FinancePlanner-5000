package Budget_stuff;

import exceptions.NegativeNumberException;
import observer.FinanceObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class FinancePlan implements MathyMath, FinanceObserver {
    float balance;
    float spending;
    float amount;
    String name;
    List<AccountControl> names = new ArrayList<>();


    public abstract void beginBudget();

    public abstract String enterStartPlan();

    public abstract void compliment();

    public void financeChooseType() {
        System.out.println("What type do you want? Beginner (0) or Expert (1)?");
    }

    public void rip() { System.out.println("Sorry you're own your own."); }

    public void fin() { System.out.println("bye bye"); }

    public void shutdown() { System.out.println("FinancePlanner shutdown."); }

    public void addName(AccountControl ac) {
        if(!names.contains(ac)) {
            names.add(ac);
            //ac.notifyObserver(ac);
        }
    }

    @Override
    public void update(AccountControl accountControl) {
        System.out.println("User at the moment: " + accountControl.displayID());
    }



    @Override
    public void fromZero() {
        balance = 0;
        amount = 0;
        spending = 0;
    }

    @Override
    public void addBalance(float amount) throws NegativeNumberException{
        balance += amount;
        if (amount < 0) {
            throw new NegativeNumberException("That's a negative number");
        }
    }

    @Override
    public void subSpending(float spending) {
        balance -= spending;
    }

    @Override
    public float retBalance() {
        return balance;
    }

    @Override
    public float retAmount() {
        return amount;
    }

    @Override
    public float retSpending() {
        return spending;
    }

}
