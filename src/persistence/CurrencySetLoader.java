package persistence;

import model.CurrencySet;

import java.sql.Connection;


public interface CurrencySetLoader {

    public CurrencySet load(Connection connection);

}
