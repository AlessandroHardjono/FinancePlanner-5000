package ui;

import Budget_stuff.FinancePlan;



public class Start {
    public static void main(String[] args) {
        FinancePlan finance_plan = new FinancePlan();
        finance_plan.beginBudget();
        finance_plan.enterStartPlan();
    }
}
