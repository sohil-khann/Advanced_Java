import java.util.Scanner;

public class _ArrayIndexOutOfBounds {
    private static final int[] data = {10, 20, 30, 40, 50};

    public static int safeAccess(int index) throws ArrayIndexOutOfBoundsException {
        if (index < 0 || index >= data.length) {
            throw new ArrayIndexOutOfBoundsException(
                "Index " + index + " is out of bounds for array length " + data.length + ".");
        }
        return data[index];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Safe Array Access Demo ===");
        System.out.println("Array contents: ");
        for (int i = 0; i < data.length; i++) {
            System.out.println("  data[" + i + "] = " + data[i]);
        }
        System.out.print("Enter an index to access: ");

        try {
            int index = scanner.nextInt();
            int value = safeAccess(index);
            System.out.println("Value at index " + index + ": " + value);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Array access attempt completed.");
        }
    }
}