package guru.springframework;

public class Dollar {
    int amount;
    public Dollar(int amount) {
        this.amount = amount;
    }

    Dollar times(int multiplier) {
        return new Dollar(amount * multiplier);
    }

    public boolean equals(Object object) {
        Dollar dollarCopy = (Dollar) object;
        return amount == dollarCopy.amount;
    }
}
