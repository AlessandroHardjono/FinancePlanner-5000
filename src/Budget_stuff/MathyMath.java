package Budget_stuff;

public class MathyMath {
    float balance = 0;
    float amount = 0;
    float spending = 0;

    // EFFECTS: resets all the variables to 0.
    public void fromZero() {
        balance = 0;
        amount = 0;
        spending = 0;
    }

    //REQUIRES: the amount (input)
    //MODIFIES: balance through addition
    //EFFECTS: the balance is added by the amount input by the user.
    public void AddBalance(float amount) {
        balance += amount;
    }

    //REQUIRES: the spending form the budget.
    //MODIFIES: the balance through subtraction.
    //EFFECTS: the balance is subtracted by amount spent.
    public void SubSpending(float spending) {
        balance -= spending;
    }

    //EFFECTS: returns balance.
    public float retBalance(){
        return balance;
    }

    //EFFECTS: returns balance.
    public float retAmount(){
        return amount;
    }

    //EFFECTS: returns balance.
    public float retSpending(){
        return spending;
    }
}
