package model;


import mock.DatabaseExchangeRateLoader;

import java.sql.SQLException;

public class Exchange {

    private final Money from;
    private final Currency to;

    public Exchange(Money from, Currency to) {
        this.from = from;
        this.to = to;
    }

    public Money getMoney() throws SQLException {
        ExchangeRate exchangeRate = new DatabaseExchangeRateLoader().load(getMoneyFrom().getCurrency(), getTo());
        return new Money(from.getAmount() * exchangeRate.getRate(), to);
    }

    public Money getMoneyFrom() {
        return from;
    }

    public Currency getTo() {
        return to;
    }

}
