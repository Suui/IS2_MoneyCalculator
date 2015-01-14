package application;

import console.ExchangeDisplay;
import control.ExchangeOperation;
import mock.CurrencySetLoader;
import model.CurrencySet;
import swing.ApplicationFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Application {


    public static void main(String[] args) throws SQLException {

        Connection connection = createConnection("currencies.db");

        CurrencySet currencySet = new CurrencySetLoader().load(connection);
        new ApplicationFrame(currencySet.toArray());
    }


    private static Connection createConnection(String databasePath) throws SQLException {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        return DriverManager.getConnection("jdbc:sqlite:" + databasePath);
    }
}
