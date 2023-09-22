import static org.junit.Assert.*;
import org.junit.Test;

// this file is only for testing purposes;

// Asked chatGPT :
// write in java a simple junit file (I am basically doing some sort of counting number of assert
// and I need a file to test my program)

// Model replied with this code and the following message:
// In this example, we have two test methods, testAssertionCount and testAnotherAssertionCount, each with their respective assertions. You can use this as a starting point to count the number of assertions in your program by running this JUnit test. You can add more test methods and assertions as needed for your specific testing requirements.
// Make sure you have JUnit properly set up in your project to run this test file.

public class test {

    @Test
    public void testAssertionCount() {
        // Perform some actions in your program
        int result = add(2, 3);

        // Perform assertions
        assertEquals("Adding 2 and 3 should equal 5", 5, result);
        assertNull("Result should be positive", result > 0);
    }

    @Test
    public void testAnotherAssertionCount() {
        // Perform some other actions in your program
        String text = "JUnit is awesome!";

        // Perform assertions
        assertNotNull("Text should not be null", text);
        assertEquals("Text should match", "JUnit is awesome!", text);
    }

    // A simple method for testing
    public int add(int a, int b) {
        return a + b;
    }
}
