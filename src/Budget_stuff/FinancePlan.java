package Budget_stuff;

public abstract class FinancePlan implements MathyMath {
    float balance;
    float spending;
    float amount;

    public abstract void beginBudget(); //{ System.out.println("Welcome to the Finance Planner 5000");}

    public abstract void enterStartPlan();

    public abstract void compliment(); //{ System.out.println("You're off to a good start!"); }

    public void financeChooseType() {
        System.out.println("What type do you want? Beginner (0) or Expert (1)?");
    }

    public void rip() { System.out.println("Sorry you're own your own."); }

    public void fin() { System.out.println("bye bye"); }

    public void shutdown() { System.out.println("FinancePlanner shutdown."); }

    @Override
    public void fromZero() {
        balance = 0;
        amount = 0;
        spending = 0;
    }

    @Override
    public void addBalance(float amount) {
        balance += amount;
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
