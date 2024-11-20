package guru.springframework;

import java.util.HashMap;

public class Bank {

    private HashMap<Pair, Integer> ratesMap = new HashMap<>();

    Money reduce(Expression source, String toCurrency) {
        return source.reduce(this, toCurrency);
    }

    public int rate(String from, String to) {
        if (from.equals(to)) {
            return 1;
        }
        Integer rate = ratesMap.get(new Pair(from, to));
        return rate;
    }

    public void addRate(String from, String to, int rate) {
        ratesMap.put(new Pair(from, to), rate);
    }
}
