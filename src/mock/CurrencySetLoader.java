package mock;

import model.CurrencySet;

import java.sql.Connection;


public class CurrencySetLoader implements persistence.CurrencySetLoader {

    @Override
    public CurrencySet load(Connection connection) {
        CurrencySet set = new DatabaseCurrencySetLoader().load(connection);
        return set;
    }

}
