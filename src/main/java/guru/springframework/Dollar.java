package guru.springframework;

public class Dollar extends Money{
    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    public Money times(int multiplier) {
        return new Money(amount * multiplier, this.currency);
    }
}
