package persistence;

import model.Currency;
import model.ExchangeRate;


public interface DatabaseExchangeRateLoader {

    public ExchangeRate load(Currency from, Currency to);

}
