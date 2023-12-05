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
    public void testConvert() {
         String curr = currencies.get(0).getShortName();
         assertSame("USD",curr);
         
        // Test converting USD to EUR
        Double result = MainWindow.convert("USD", "EUR", currencies, 100.0);
        assertSame(93,result);

        // Test converting EUR to USD
        result = MainWindow.convert("EUR", "USD", currencies, 100.0);
        assertSame(107.3, result);

        // Add more test cases
    }

}