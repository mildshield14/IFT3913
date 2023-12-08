package test;

import currencyConverter.Currency;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;

public class Test_boite_Noire {

    private static ArrayList<Currency> currencies;
    private static HashMap<String, Double> exchangeRates;
    @Before
    public void setUp() {
        currencies = Currency.init();
        // Prendre les taux de change de l'USD
        exchangeRates = currencies.stream()
                .filter(c -> "USD".equals(c.getShortName()))
                .findFirst()
                .orElseThrow()
                .getExchangeValues();
    }

    // Analyse des valeurs limites pour la méthode currencyConverter.Currency.convert(Double, Double),
    // testons les montants qui débordent l'intervalle [0,1 000 000]
    @Test
    public void testCurrencyConvertNegativeBoundary() {
        // Testons un montant négatif, il devrait lancer une exception.
        double amount = -0.01;

        // Ici, nous choisissons le taux d'exchange pour convertir EUR en JPY
        double exchangeRate = currencies.get(2).getExchangeValues().get("JPY");
        assertThrows("La conversion d'un montant négatif devrait lancer IllegalArgumentException",IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate));
    }
    @Test
    public void testCurrencyConvertAboveUpperBoundary() {
        // Testons un montant déborde la valeur maximum, il devrait lancer une exception
        double amount = 1000000.01;

        // Ici, nous choisissons le taux d'exchange pour convertir Swiss Franc en Chinese Yuan Renminbi
        double exchangeRate = currencies.get(3).getExchangeValues().get("CNY");
        assertThrows("La conversion au-dessus de la limite supérieure devrait lancer IllegalArgumentException",IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate));}

    // Testons les montants entre l'intervalle [0,1 000 000]
    @Test
    public void testCurrencyConvertLowerBoundary() {
        //Testons un montant de limite inférieure, il devrait retour la valeur 0.0
        double amount = 0.00;

        // Ici, nous choisissons le taux d'exchange pour convertir Euro en USD
        double exchangeRate = currencies.get(1).getExchangeValues().get("USD");
        double result = Currency.convert(amount, exchangeRate);
        assertEquals(0.00, result, 0.01);
    }

    @Test
    public void testCurrencyConvertTypicalValue() {
        // Testons un montant typique dans les limites
        double amount = 2500.00;

        // Ici, nous choisissons le taux d'exchange pour convertir EUR en CHF
        double exchangeRate = currencies.get(1).getExchangeValues().get("CHF");
        double expected = Math.round(amount * exchangeRate * 100.0) / 100.0; // Valeur attendue après la conversion
        double result = Currency.convert(amount, exchangeRate); // valeur réelle attendue après la conversion
        assertEquals(expected, result, 0.01);
    }

    @Test
    public void testCurrencyConvertUpperBoundary() {
        // Testons un montant de limite supérieure
        double amount = 1000000.00;

        // Ici, nous choisissons le taux d'exchange pour convertir GBP en CHF
        double exchangeRate = currencies.get(2).getExchangeValues().get("CHF");

        // Valeur attendue après la conversion
        double expected = Math.round(amount * exchangeRate * 100.0) / 100.0;

        // valeur réelle attendue après la conversion
        double result = Currency.convert(amount, exchangeRate);
        assertEquals(expected, result, 0.01);
    }

    // Test de classe d'équivalence
    @Test
    public void testValidAmountConversion() {
        double amount = 500000.00;
        double exchangeRate = exchangeRates.get("JPY"); // Taux de change USD vers JPY
        double expected = Math.round(amount * exchangeRate * 100d) / 100d; // Résultat de conversion attendu
        double actual = Currency.convert(amount, exchangeRate); // Résultat de conversion réel
        assertEquals(expected, actual, 0.01);
    }

    // Test de classe d'équivalence invalide: montants inférieurs à 0
    @Test
    public void testNegativeAmountConversion() {
        double amount = -1.00; // Montant invalide (inférieur à 0)
        double exchangeRate = exchangeRates.get("CHF"); // USD to CHF exchange rate
        // Assuming convert should return null or throw an exception for invalid input
        assertThrows("La conversion devrait échouer pour les montants négatifs.",IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate)
                );
    }

    // Test de classe d'équivalence invalide : montants supérieurs à 1000000
    @Test
    public void testExcessiveAmountConversion() {
        double amount = 1000000.01;
        double exchangeRate = exchangeRates.get("GBP"); // Taux de change USD vers GBP
        // En supposant que convert doive renvoyer null ou lever une exception pour une entrée non valide
        assertThrows("La conversion devrait échouer pour les montants dépassant la limite maximale",IllegalArgumentException.class,
                () -> Currency.convert(amount, exchangeRate)
                );
    }
}



