package guru.springframework;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {
    @Test
    void testMultiplication() {
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));

        Money fiveF = Money.franc(5);
        assertEquals(Money.franc(10), fiveF.times(2));
    }


    @Test
    void testEqualityDollar() {
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertEquals(Money.franc(5), Money.franc(5));
        assertNotEquals(Money.dollar(5), Money.franc(5));
    }

    @Test
    void testCurrency() {
        assertEquals("USD", Money.dollar(5).currency());
        assertEquals("CHF", Money.franc(5).currency());

    }

    @Test
    void testSimpleAddition() {
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void testPlusReturnsSum() {
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five, sum.augmend);
        assertEquals(five, sum.augmend);
    }

    @Test
    void testReduceSum() {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), reduced);
    }

    @Test
    void testReduceMoney() {
        Bank bank = new Bank();
        Money reduced = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), reduced);
    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money reduced = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), reduced);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
        assertEquals( 1, new Bank().rate("CHF", "CHF"));
    }

    @Test
    public void testMixedAddition() {
        Expression five = Money.dollar(5);
        Expression ten = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Money reduced = bank.reduce(five.plus(ten), "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    public void testSumTimes() {
        Expression five = Money.dollar(5);
        Expression ten = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate("CHF", "USD", 2);
        Expression sum = new Sum(five, ten).times(2);
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), reduced);
    }
}
