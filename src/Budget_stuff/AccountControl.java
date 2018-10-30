package Budget_stuff;

public class AccountControl {
    public String name;

    public void stateName() { System.out.println("State your name: "); }

    public void establishUser(String name) {
        this.name = name;
    }
}
