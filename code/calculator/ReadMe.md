# Flexible Calculator

This project implements a simple, extensible calculator that supports multiple operations. The calculator is designed with the Open-Closed Principle in mind, allowing new operations to be added without modifying the existing code.

## Features

- Basic operations: ADD, SUBTRACT, MULTIPLY, DIVIDE
- Support for chaining multiple operations
- Extensible with new operations
- Handles invalid operations gracefully
- IoC compatible for dependency injection

## Usage

1.  Use the `calculate` method for basic operations:
  
    Calculator calculator = new Calculator();
    double result = calculator.calculate(Operation.ADD, 5.0, 3.0);
   
2.  Use the chainOperations method for performing multiple operations sequentially:
	
    Object[][] operations = {
	    {Operation.ADD, 3.0},
	    {Operation.MULTIPLY, 2.0}
	};
	double result = calculator.chainOperations(5.0, operations);
	
3.  Add new operations dynamically:	
    
    calculator.addOperation(Operation.POWER, (num1, num2) -> Math.pow(num1, num2));
    

