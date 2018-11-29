package observer;

import Budget_stuff.AccountControl;

public interface FinanceObserver {

    void update(AccountControl accountControl);
}
