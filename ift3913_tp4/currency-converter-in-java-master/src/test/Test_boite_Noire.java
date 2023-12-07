package test;
import currencyConverter.MainWindow;
import currencyConverter.Currency;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class Test_boite_Noire {

    private static ArrayList<Currency> currencies;

    @BeforeAll
    public static void setUp() {
        currencies = Currency.init();
    }

    // Analyse des valeurs limites pour la méthode currencyConverter.Currency.convert(Double, Double),
    // testons les montants qui débordent l'intervalle [0,1 000 000]
    @Test
    public void testCurrencyConvertNegativeBoundary() {
        // Testons un montant négatif, il devrait lancer une exception.
        double amount = -0.01;

        // Ici, nous choisissons le taux d'exchange pour convertir EUR en JPY
        double exchangeRate = currencies.get(2).getExchangeValues().get("JPY");
        assertThrows(IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate),
                "La conversion d'un montant négatif devrait lancer IllegalArgumentException");
    }
    @Test
    public void testCurrencyConvertAboveUpperBoundary() {
        // Testons un montant déborde la valeur maximum, il devrait lancer une exception
        double amount = 1000000.01;

        // Ici, nous choisissons le taux d'exchange pour convertir Swiss Franc en Chinese Yuan Renminbi
        double exchangeRate = currencies.get(3).getExchangeValues().get("CNY");
        assertThrows(IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate),
                "La conversion au-dessus de la limite supérieure devrait lancer IllegalArgumentException");}

    // Testons les montants entre l'intervalle [0,1 000 000]
    @Test
    public void testCurrencyConvertLowerBoundary() {
        //Testons un montant de limite inférieure, il devrait retour la valeur 0.0
        double amount = 0.00;

        // Ici, nous choisissons le taux d'exchange pour convertir Euro en USD
        double exchangeRate = currencies.get(1).getExchangeValues().get("USD");
        double result = Currency.convert(amount, exchangeRate);
        assertEquals(0.00, result, "Conversion at lower boundary should be 0");
    }

    @Test
    public void testCurrencyConvertTypicalValue() {
        // Testons un montant typique dans les limites
        double amount = 2500.00;

        // Ici, nous choisissons le taux d'exchange pour convertir EUR en CHF
        double exchangeRate = currencies.get(1).getExchangeValues().get("CHF");
        double expected = Math.round(amount * exchangeRate * 100.0) / 100.0; // Valeur attendue après la conversion
        double result = Currency.convert(amount, exchangeRate); // valeur réelle attendue après la conversion
        assertEquals(expected, result, "Conversion of typical amount should be correct and match the expected value");
    }

    @Test
    public void testCurrencyConvertUpperBoundary() {
        // Testons un montant de limite supérieure
        double amount = 1000000.00;

        // Ici, nous choisissons le taux d'exchange pour convertir GBP en CHF
        double exchangeRate = currencies.get(2).getExchangeValues().get("CHF");
        double expected = Math.round(amount * exchangeRate * 100.0) / 100.0; // Valeur attendue après la conversion
        double result = Currency.convert(amount, exchangeRate);// valeur réelle attendue après la conversion
        assertEquals(expected, result, "Conversion at upper boundary should be correct and match the expected value");
    }


    //Analyse des valeurs limites pour la méthode currencyConverter.MainWindow.convert(String, String, ArrayList<Currency>, Double)
    //testons les montants qui débordent l'intervalle [0,1 000 000]
    @Test
    public void testMainWindowConvertBelowLowerBoundary() {
        // Testons un montant négatif, il devrait lancer une exception
        double amount = -1.0;
        String fromCurrency = "US Dollar";
        String toCurrency = "CYN";
        assertThrows(IllegalArgumentException.class,
                () -> MainWindow.convert(fromCurrency, toCurrency, currencies, amount),
                "Conversion should not be possible for negative amounts");
    }
    @Test
    public void testMainWindowConvertJustAboveUpperBoundary() {
        // Testons un montant déborde la valeur maximum, il devrait lancer une exception
        double amount = 1000000.01;
        String fromCurrency = "Chinese Yuan Renminbi";
        String toCountryCurrency = "EUR";
        assertThrows(IllegalArgumentException.class,
                () -> MainWindow.convert(fromCurrency, toCountryCurrency, currencies, amount),
                "Conversion should not be possible for amounts above 1,000,000");
    }
    @Test
    public void testMainWindowConvertAtLowerBoundary() {
        // Testons un montant de limite inférieure, il devrait retour la valeur 0.0
        double amount = 0.00;
        String fromCurrency = "US Dollar";
        String toCurrency = "Euro";
        assertEquals(0.00, MainWindow.convert(fromCurrency, toCurrency, currencies, amount),
                "Conversion at lower boundary should be 0");
    }
    @Test
    public void testMainWindowConvertAtTypicalBoundary() {
        //Testons un montant typique dans les limites
        double amount = 55000.00;
        String fromCurrency = "Chinese Yuan Renminbi";
        String toCurrency = "British Pound";

        // valeur réelle attendue après la conversion
        double exceptedPriceM = amount * currencies.get(4).getExchangeValues().get("GBP");
        assertEquals(exceptedPriceM,MainWindow.convert(fromCurrency, toCurrency, currencies, amount),
                "Conversion at upper boundary should result in a positive value");

    }

    @Test
    public void testMainWindowConvertAtBoundary() {
        // Testons un montant de limite supérieure
        double amount = 1000000.00;

        String fromCurrency = "British Pound";
        String toCurrency = "Japanese Yen";

        // valeur réelle attendue après la conversion
        double exceptedPriceM = amount * currencies.get(2).getExchangeValues().get("JPY");
        assertEquals(exceptedPriceM,MainWindow.convert(fromCurrency, toCurrency, currencies, amount),
                "Conversion at upper boundary should result in a positive value");
    }


}


