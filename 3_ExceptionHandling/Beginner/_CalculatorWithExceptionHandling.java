import java.util.InputMismatchException;
import java.util.Scanner;

public class _CalculatorWithExceptionHandling {
    public static void calculate(double a, double b, char operator)
            throws ArithmeticException, IllegalArgumentException {
        double result;
        switch (operator) {
            case '+':
                result = a + b;
                System.out.println("Result: " + a + " + " + b + " = " + result);
                break;
            case '-':
                result = a - b;
                System.out.println("Result: " + a + " - " + b + " = " + result);
                break;
            case '*':
                result = a * b;
                System.out.println("Result: " + a + " * " + b + " = " + result);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                result = a / b;
                System.out.println("Result: " + a + " / " + b + " = " + result);
                break;
            default:
                throw new IllegalArgumentException("Invalid operator: '" + operator + "'. Use +, -, *, or /.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Simple Calculator with Exception Handling ===");

        try {
            System.out.print("Enter first number: ");
            double a = scanner.nextDouble();
            System.out.print("Enter an operator (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            System.out.print("Enter second number: ");
            double b = scanner.nextDouble();

            calculate(a, b, operator);
        } catch (ArithmeticException e) {
            System.out.println("Calculation Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: Please enter valid numeric values.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Calculator session ended.");
        }
    }
}