package Budget_stuff;

public class ExpertFinance extends FinancePlan {
    @Override
    public void beginBudget() {
        System.out.println("Welcome to the Finance Planner 5000: Expert Edition");
    }

    @Override
    public String enterStartPlan() {
        String output = "What does the beginner want to plan?\n" +
                "1. Add an amount to your balance\n" +
                "2. Enter Spending\n" +
                "3. End the program\n" +
                "4. Display base currency and today's date";

        return output;
    }

    @Override
    public void compliment() {
        System.out.println("A true expert!");
    }
}
