package mock;

import model.Currency;
import model.CurrencySet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DatabaseCurrencySetLoader implements persistence.CurrencySetLoader{


    @Override
    public CurrencySet load(Connection connection) {
        try {
            return processCurrencyInfo(connection.createStatement().executeQuery("SELECT * FROM currency_info"));
        } catch (SQLException e) {
            e.printStackTrace();
            return new CurrencySet();
        }
    }


    private CurrencySet processCurrencyInfo(ResultSet resultSet) throws SQLException {
        CurrencySet set = new CurrencySet();
        while(resultSet.next())
            set.add(processCurrency(resultSet));
        return set;
    }


    private Currency processCurrency(ResultSet resultSet) throws SQLException {
        return new Currency(
                resultSet.getString("code"),
                resultSet.getString("name"),
                resultSet.getString("symbol")
        );
    }

}
