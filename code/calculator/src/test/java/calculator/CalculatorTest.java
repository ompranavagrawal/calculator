package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAddition() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(5.0, calculator.calculate(Operations.ADD, 2.0, 3.0));
    }

    @Test
    public void testDivision() {
        Calculator calculator = new Calculator();
        Assertions.assertEquals(2.0, calculator.calculate(Operations.DIVIDE, 6.0, 3.0));
    }

    @Test
    public void testDivisionByZero() {
        Calculator calculator = new Calculator();
        Assertions.assertThrows(ArithmeticException.class, () -> calculator.calculate(Operations.DIVIDE, 6.0, 0.0));
    }

    @Test
    public void testChainedOperations() {
        Calculator calculator = new Calculator();
        Object[][] operations = {
            {Operations.ADD, 3.0},
            {Operations.MULTIPLY, 2.0}
        };
        double result = calculator.chainOperations(5.0, operations);
        Assertions.assertEquals(16.0, result);
    }

    @Test
    public void testUnsupportedOperation() {
        Calculator calculator = new Calculator();
        Assertions.assertThrows(UnsupportedOperationException.class, () -> calculator.calculate(null, 2.0, 3.0));
    }
    
    //test for the dynamically added Power operation
    @Test
    public void testPowerOperation() {
        Calculator calculator = new Calculator();

        // Adding the Power operation dynamically using a string identifier
        calculator.addOperation("POWER", (num1, num2) -> Math.pow(num1, num2));

        // Perform the Power operation and verify the result
        double result = calculator.calculate("POWER", 2.0, 3.0);
        Assertions.assertEquals(8.0, result, "2 raised to the power 3 should be 8.0");
    }
}
