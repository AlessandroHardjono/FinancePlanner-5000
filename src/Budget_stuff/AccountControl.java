package Budget_stuff;

import exceptions.NegativeNumberException;
import ui.FinancePlanner;

import java.io.IOException;
import java.util.*;
//import java.util.Set;

public class AccountControl{
    private String name;
    private ArrayList<String> names;
    private int type;
    private ArrayList<Integer> types;
    Map<AccountControl, String> identifyType;

    public AccountControl(String name, int type)  {
        identifyType = new HashMap<>();
        names = new ArrayList<>();
        this.name = name;
        this.type = type;
        types = new ArrayList<>();
    }

    public void stateName() { System.out.println("State your name: "); }

    public void establishUser(String name) {
        this.name = name;
    }

    public void addUser(String name) {
        this.names.add(name);
    }

//    public void establishType(int type) {
//        this.type = type;
//    }

    public void addType(int type) {
        this.types.add(type);
    }

    public String displayID() {
        String accounts = new String();

        Set<AccountControl> iD = identifyType.keySet();
        int i;
        for (i=0; i<iD.size(); i++) {
            accounts = accounts.concat("Name: " + names.get(i));
        }

        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountControl ac = (AccountControl) o;
        return name == ac.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    //public void initializeID()

    public Map<AccountControl, String> retID() { return identifyType; }
}
