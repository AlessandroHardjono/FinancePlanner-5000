package ui;

import Budget_stuff.MathyMath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class FinancePlannerTest {
    private MathyMath mathFinance;

    @BeforeEach
    public void runBefore() { mathFinance = new MathyMath(); }

    @Test
    public void testAddBalance(){
        mathFinance.fromZero();
        mathFinance.AddBalance(500);
        assertEquals(500, mathFinance.retBalance());
    }

    @Test
    public void testSubSpending(){
        mathFinance.fromZero();
        mathFinance.AddBalance(500);
        mathFinance.SubSpending(300);
        assertEquals(200, mathFinance.retBalance());
    }

    @Test
    public void testFromZero(){
        mathFinance.AddBalance(500);
        mathFinance.SubSpending(300);
        mathFinance.fromZero();
        assertEquals(0, mathFinance.retBalance());
        assertEquals(0, mathFinance.retAmount());
        assertEquals(0, mathFinance.retSpending());
    }

}