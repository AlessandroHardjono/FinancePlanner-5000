package ui;

import Budget_stuff.BeginnerFinance;
import Budget_stuff.FinancePlan;
import exceptions.NegativeNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FinancePlannerTest {
    private FinancePlan mathFinance;

    @BeforeEach
    public void runBefore() {
        mathFinance = new BeginnerFinance();
    }

    @Test
    public void testAddBalance() throws NegativeNumberException {
        mathFinance.fromZero();
        mathFinance.addBalance(500);
        assertEquals(500, mathFinance.retBalance());
    }

    @Test
    public void testSubSpending() throws NegativeNumberException {
        mathFinance.fromZero();
        mathFinance.addBalance(500);
        mathFinance.subSpending(300);
        assertEquals(200, mathFinance.retBalance());
    }

    @Test
    public void testFromZero() throws NegativeNumberException {
        mathFinance.addBalance(500);
        mathFinance.subSpending(300);
        mathFinance.fromZero();
        assertEquals(0, mathFinance.retBalance());
        assertEquals(0, mathFinance.retAmount());
        assertEquals(0, mathFinance.retSpending());
    }

    @Test
    public void testMisMatchException() {
        try {
            mathFinance.addBalance(-500);
            fail("still negative");
        } catch (NegativeNumberException e) {
            System.out.println("pass");
        }
    }

}