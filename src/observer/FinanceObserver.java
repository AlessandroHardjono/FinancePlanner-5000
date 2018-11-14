package observer;

import Budget_stuff.AccountControl;
//import Budget_stuff.BeginnerFinance;

public interface FinanceObserver {

    void update(AccountControl accountControl);
}
