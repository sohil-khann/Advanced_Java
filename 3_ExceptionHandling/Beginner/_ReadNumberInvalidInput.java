import java.util.InputMismatchException;
import java.util.Scanner;

public class _ReadNumberInvalidInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Number Reader (Handles Invalid Input) ===");
        System.out.println("Enter a number as text or integer:");

        try {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("No input provided.");
            }

            int number = Integer.parseInt(input);
            System.out.println("Successfully parsed integer: " + number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: The input is not a valid integer.");
            System.out.println("Details: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("InputMismatchException: The input does not match expected type.");
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Number reading process finished.");
        }
    }
}