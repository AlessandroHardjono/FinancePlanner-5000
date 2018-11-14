package observer;

import Budget_stuff.AccountControl;
//import Budget_stuff.BeginnerFinance;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<FinanceObserver> observers = new ArrayList<>();

    public void addObserver(FinanceObserver financeObserver) {
        if(!observers.contains(financeObserver)){
            observers.add(financeObserver);
        }
    }

    public void notifyObserver(AccountControl ac) {
        for (FinanceObserver observer : observers) {
            observer.update(ac);
        }
    }


}
