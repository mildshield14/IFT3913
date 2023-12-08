package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.beans.Transient;
import java.util.ArrayList;

public class Method1_tests {
    private ArrayList<String> names;
    private ArrayList<Currency> currencies;

    @Before
    public void init(){
        // Create test data;
        currencies=Currency.init();

        names = new ArrayList<>();

        for (int i=0;i<currencies.size();i++){
            names.add(currencies.get(i).getShortName());
        }
        
    }

    @Test
    public void testNameUSD(){
        assertEquals(names.contains("USD"),true);
    }

    @Test
    public void testNameCAD(){
        assertEquals(names.contains("CAD"),true);
    }

     @Test
    public void testNameEUR(){
        assertEquals(names.contains("EUR"),true);
    }

     @Test
    public void testNameCHF(){
        assertEquals(names.contains("CHF"),true);
    }

     @Test
    public void testNameAUD(){
        assertEquals(names.contains("AUD"),true);
    }

     @Test
    public void testInvalidCurrencyXXX(){
        assertEquals(names.contains("XXX"),false);
    }

    @Test
    public void testNullArray(){
         Double result = MainWindow.convert("USD", "EUR", new ArrayList<>(), 100.0);
         assertEquals(0.0, result,0.01);
    }

    @Test
    public void testConversion() {
        String curr = currencies.get(0).getShortName();
        assertSame("USD",curr);

        // Test converting USD to EUR
        Double result = MainWindow.convert("USD", "EUR", currencies, 100.0);
        assertSame(93,result);

        // Test converting EUR to USD
        result = MainWindow.convert("EUR", "USD", currencies, 100.0);
        assertSame(107.3, result);

        // Test converting EUR to CAD
        result = MainWindow.convert("EUR", "CAD", currencies, 10.0);
        assertSame(10, result);

        // Test converting EUR to CAD
        result = MainWindow.convert("EUR", "CHF", currencies, 10.0);
        assertSame(10, result);

        // Test converting EUR to CAD
        result = MainWindow.convert("EUR", "AUD", currencies, 10.0);
        assertSame(10, result);
    }

    @Test
    public void testInvalidCurrency() {
        // Test with an invalid currency
        Double result = MainWindow.convert("USD", "XYZ", currencies, 100.0);
        assertEquals(0.0, result, 0.01);
    }

    @Test
    public void testInvalidAmount() {
        // Test with an invalid amount
        Double result = MainWindow.convert("USD", "EUR", currencies, -100.0);
        assertEquals(0.0, result, 0.01);

    }

    @Test
    public void testBoundariesLower() {

        // Test with boundary values [0,1000000] here 0
        Double result = MainWindow.convert("USD", "EUR", currencies, 0.0);
        assertEquals(0.0, result, 0.0);

    }

     @Test
    public void testBoundariesUpper() {

        // Test with boundary values [0,1000000] here 1000000
        
        Double result3 = MainWindow.convert("USD", "EUR", currencies, 1000000.0);
        assertEquals(1000000, result3, 0.0);

    }

     @Test
    public void testInvalidUpper() {
        // Test with an invalid amount > boundaries value
        assertThrows("La conversion devrait échouer pour les montants dépassant la limite maximale",IllegalArgumentException.class,
                () -> MainWindow.convert("USD", "EUR", currencies, 1000000.01)
                );
    }

    @Test
    public void testInvalidLower() {
        assertThrows("La conversion devrait échouer pour les montants dépassant la limite maximale",IllegalArgumentException.class,
                () -> MainWindow.convert("USD", "EUR", currencies, -0.01)
                );
    }

}
