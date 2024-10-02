
# Flexible Calculator

This project implements a simple, extensible calculator in Java that supports multiple operations. The calculator is designed following key object-oriented principles, especially the Open-Closed Principle, ensuring maintainability and extensibility. 

## Features

- **Basic operations**: `ADD`, `SUBTRACT`, `MULTIPLY`, and `DIVIDE`
- **Chaining operations**: Ability to chain multiple operations sequentially
- **Extensibility**: Allows dynamic addition of new operations (e.g., `POWER`)
- **IoC Compatibility**: Designed to be compatible with Inversion of Control (IoC) environments, making it easy to manage dependencies
- **Error Handling**: Gracefully handles unsupported operations and invalid inputs
- **Unit Testing**: Comprehensive unit tests for normal cases, edge cases, and dynamically added operations

## How to Run

### Prerequisites

Ensure you have the following installed:
- Java 8 or higher
- Maven (if you're using Maven for build management)

### Steps to Run

1. Clone the repository:

2. Navigate to the project directory:

3. Compile the project:

4. Run the project:

You should see output from various calculations, including both basic and dynamically added operations.

### Example Output:
```
5 + 3 = 8.0
10 - 4 = 6.0
7 * 2 = 14.0
8 / 2 = 4.0
2 ^ 3 = 8.0
Chained result = 16.0
```

## Design Overview

### 1. **Operations Enum**

We defined an enum named `Operations` that includes basic operations like `ADD`, `SUBTRACT`, `MULTIPLY`, and `DIVIDE`. The enum ensures that the calculator can identify and execute these operations efficiently.

```java
public enum Operations {
    ADD, SUBSTRACT, MULTIPLY, DIVIDE
}
```

### 2. **Basic Calculation Method**

The core method `calculate(Operations op, double num1, double num2)` performs the required operation between two numbers. It adheres to the Open-Closed Principle by allowing operations to be dynamically added via a strategy pattern.

```java
public double calculate(Operations op, double num1, double num2) {
    OperationStrategy operation = operationsMap.get(op);
    if (operation == null) {
        throw new UnsupportedOperationException("Operation " + op + " is not supported");
    }
    return operation.execute(num1, num2);
}
```

### 3. **Chaining Operations**

The method `chainOperations` allows the chaining of multiple operations. You can pass an initial value and a sequence of operations to apply them sequentially:

```java
public double chainOperations(double initialValue, Object[][] operations) {
    double result = initialValue;
    for (Object[] operation : operations) {
        Operations op = (Operations) operation[0];
        double value = (double) operation[1];
        result = calculate(op, result, value);
    }
    return result;
}
```

### 4. **Extensibility**

You can dynamically add new operations (like `POWER`) without modifying the `Calculator` class itself. This is done via the `addOperation` method, which allows adding new operations at runtime:

```java
calculator.addOperation("POWER", (num1, num2) -> Math.pow(num1, num2));
```

### 5. **IoC Compatibility**

The `Calculator` class is designed to be compatible with IoC environments. It uses constructor injection to allow external management of dependencies:

```java
public Calculator(Map<Operations, OperationStrategy> operationsMap) {
    this.operationsMap = operationsMap;
}
```

This enables you to inject dependencies (such as operations) from an IoC container like Spring.

### 6. **Error Handling**

The calculator gracefully handles invalid operations by throwing exceptions for unsupported operations and division by zero:

```java
if (num2 == 0) {
    throw new ArithmeticException("Cannot divide by zero");
}
```

### 7. **Unit Testing**

We have written comprehensive unit tests using JUnit 5 to verify the correctness of the calculator's functionality, including handling of basic operations, edge cases (like division by zero), and dynamically added operations:

```java
@Test
public void testPowerOperation() {
    Calculator calculator = new Calculator();
    calculator.addOperation("POWER", (num1, num2) -> Math.pow(num1, num2));
    double result = calculator.calculate("POWER", 2.0, 3.0);
    Assertions.assertEquals(8.0, result);
}
```

## Testing

You can run the tests using Maven or any other build tool you are using:

```bash
mvn test
```

Tests include:
- **Addition, Subtraction, Multiplication, Division**: Test basic operations.
- **Division by Zero**: Ensures appropriate exception handling.
- **Unsupported Operation**: Tests that an unsupported operation throws an exception.
- **Chaining Operations**: Verifies that multiple operations can be applied in sequence.
- **Dynamically Added Operations**: Tests adding new operations like `POWER` at runtime.

### Example Test Output:
```
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
```

## Assumptions & Design Decisions

1. **Operations Enum**: We decided to define the basic operations (`ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`) in an enum for type safety and clarity.
2. **Extensibility**: The calculator is designed to allow new operations to be added without modifying the core code, following the Open-Closed Principle.
3. **IoC Compatibility**: By designing the `Calculator` class with constructor injection, we ensured compatibility with IoC containers for better dependency management.
4. **Dynamic Operations**: We added support for dynamic operations (e.g., `POWER`) to provide flexibility without modifying the core calculator class.

## Conclusion

This project showcases a flexible, extensible calculator that adheres to good software engineering practices, including maintainability, extensibility, and compatibility with modern IoC environments.

Feel free to explore the code and add more operations or features!


