import java.util.Scanner;

public class _DivisionByZero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Division By Zero Handler ===");
        System.out.print("Enter the first number (dividend): ");
        double dividend = scanner.nextDouble();
        System.out.print("Enter the second number (divisor): ");
        double divisor = scanner.nextDouble();

        try {
            double result = dividend / divisor;
            System.out.println("Result: " + dividend + " / " + divisor + " = " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Operation completed.");
        }
    }
}