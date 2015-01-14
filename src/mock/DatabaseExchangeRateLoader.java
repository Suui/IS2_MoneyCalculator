package mock;

import model.Currency;
import model.ExchangeRate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExchangeRateLoader implements persistence.DatabaseExchangeRateLoader{


    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return searchExchangeRate(from, to);
        } catch (SQLException e) {
            //e.printStackTrace();
            return null;
        }
    }


    private ExchangeRate searchExchangeRate(Currency from, Currency to) throws SQLException {
        Connection connection = createConnection("currencies.db");

        ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM currency_rates " +
                "WHERE currency_from='" + from.getCode() + "' AND currency_to='" + to.getCode() +"'");
        if (resultSet.next())
            return new ExchangeRate(from, to, resultSet.getFloat("rate"));

        resultSet = connection.createStatement().executeQuery("SELECT * FROM currency_rates " +
                "WHERE currency_from='" + to.getCode() + "' AND currency_to='" + from.getCode() +"'");
        if (resultSet.next())
            return new ExchangeRate(from, to, 1.0/resultSet.getFloat("rate"));

        return null;
    }


    private static Connection createConnection(String databasePath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }

}
