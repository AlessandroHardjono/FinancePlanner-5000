package Budget_stuff;

public class FinancePlan implements MathyMath {
    float balance;
    float spending;
    float amount;

    public void beginBudget(){
        System.out.println("Welcome to the Finance Planner 5000");
    }

    public void enterStartPlan(){
        System.out.println("What do you want to plan?");
        System.out.println("1. Add an amount to your balance");
        System.out.println("2. Enter spending");
        System.out.println("3. End the program");
    }

    public void compliment() { System.out.println("You're off to a good start!"); }

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
