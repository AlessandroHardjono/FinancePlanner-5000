package Budget_stuff;


public interface MathyMath {
//    float balance;
//    float amount;
//    float spending;


    // EFFECTS: resets all the variables to 0.
    void fromZero();
//        balance = 0;
//        amount = 0;
//        spending = 0;

    //REQUIRES: the amount (input)
    //MODIFIES: balance through addition
    //EFFECTS: the balance is added by the amount input by the user.
    void addBalance(float amount);

    //REQUIRES: the spending form the budget.
    //MODIFIES: the balance through subtraction.
    //EFFECTS: the balance is subtracted by amount spent.
    void subSpending(float spending);
//        balance -= spending;

    //EFFECTS: returns balance.
    float retBalance();
//        return balance;

    //EFFECTS: returns balance.
    float retAmount();

    //EFFECTS: returns balance.
    float retSpending();
}
