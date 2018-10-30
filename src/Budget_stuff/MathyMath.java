package Budget_stuff;

import exceptions.NegativeNumberException;

public interface MathyMath {


    // EFFECTS: resets all the variables to 0.
    void fromZero();

    //REQUIRES: the amount (input)
    //MODIFIES: balance through addition
    //EFFECTS: the balance is added by the amount input by the user.
    void addBalance(float amount) throws NegativeNumberException;

    //REQUIRES: the spending form the budget.
    //MODIFIES: the balance through subtraction.
    //EFFECTS: the balance is subtracted by amount spent.
    void subSpending(float spending);

    //EFFECTS: returns balance.
    float retBalance();

    //EFFECTS: returns balance.
    float retAmount();

    //EFFECTS: returns balance.
    float retSpending();
}
