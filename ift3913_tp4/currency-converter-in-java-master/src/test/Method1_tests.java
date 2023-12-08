import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class Method1_tests {

    private ArrayList<Currency> currencies;

    @Before
    public void init(){
        // Create test data;
        currencies=currencyConverter.Currency.init();
        
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
    public void testBoundaries() {

        // Test with boundary values [0,1000000]
        Double result = MainWindow.convert("USD", "EUR", currencies, 0.0);
        assertEquals(0.0, result, 0.0);

        Double result2 = MainWindow.convert("USD", "EUR", currencies, 0.0);
        assertEquals(0.0, result2, 0.0);
        
        Double result3 = MainWindow.convert("USD", "EUR", currencies, 1000000.0);
        assertEquals(1000000, result3, 0.0);

    }

     @Test
    public void testInvalid() {
        // Test with an invalid amount > boundaries value
        Double result = MainWindow.convert("USD", "EUR", currencies, 1000001.0);
        assertEquals(928840.93, result, 0.01);
    }

}
