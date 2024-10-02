package calculator;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    // Define an interface for operation strategy
    interface OperationStrategy {
        double execute(double num1, double num2);
    }

    // Map to store the supported operations
    private final Map<Object, OperationStrategy> operationsMap;

    // Constructor Injection for IoC compatibility
    public Calculator(Map<Object, OperationStrategy> operationsMap) {
        this.operationsMap = operationsMap;
    }

    // Basic constructor to initialize with default operations if not provided externally
    public Calculator() {
        this.operationsMap = new HashMap<>();
        operationsMap.put(Operations.ADD, (num1, num2) -> num1 + num2);
        operationsMap.put(Operations.SUBSTRACT, (num1, num2) -> num1 - num2);
        operationsMap.put(Operations.MULTIPLY, (num1, num2) -> num1 * num2);
        operationsMap.put(Operations.DIVIDE, (num1, num2) -> {
            if (num2 == 0) {
                throw new ArithmeticException("Cannot divide by zero");
            }
            return num1 / num2;
        });
    }

    // Basic calculation method
    public double calculate(Object op, double num1, double num2) {
        OperationStrategy operation = operationsMap.get(op);
        if (operation == null) {
            throw new UnsupportedOperationException("Operation " + op + " is not supported");
        }
        return operation.execute(num1, num2);
    }

    // Method to add new operations dynamically
    public void addOperation(Object op, OperationStrategy strategy) {
        operationsMap.put(op, strategy);
    }

    // Method for chaining multiple operations
    public double chainOperations(double initialValue, Object[][] operations) {
        double result = initialValue;
        for (Object[] operation : operations) {
            Object op = operation[0];
            double value = (double) operation[1];
            result = calculate(op, result, value);
        }
        return result;
    }
}
