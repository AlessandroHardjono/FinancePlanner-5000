package Budget_stuff;

public class BeginnerFinance extends FinancePlan {

    @Override
    public void beginBudget() {
        System.out.println("Welcome to the Finance Planner 5000: Beginner Edition");
    }

    @Override
    public void enterStartPlan() {
        System.out.println("What does the beginner want to plan?");
        System.out.println("1. Add an amount to your balance");
        System.out.println("2. Enter spending");
        System.out.println("3. End the program");
        System.out.println("4. Display Welcome Page");
    }

    @Override
    public void compliment() {
        System.out.println("That's a fair start!");
    }
}
