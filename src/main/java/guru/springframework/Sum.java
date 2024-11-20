package guru.springframework;

public class Sum implements Expression {
    Expression augmend;
    Expression addmend;

    public Sum(Expression augmend, Expression addmend) {
        this.augmend = augmend;
        this.addmend = addmend;
    }

    public Money reduce(Bank bank, String to) {
        int amount = addmend.reduce(bank, to).amount + augmend.reduce(bank, to).amount;
        return new Money(amount , to);
    }

    @Override
    public Expression plus(Expression addend) {
        return null;
    }
}
