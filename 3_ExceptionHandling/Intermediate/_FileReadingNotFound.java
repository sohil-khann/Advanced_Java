import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class _FileReadingNotFound {
    public static void readFile(String path) throws FileNotFoundException, IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + path);
        }
        Scanner fileScanner = new Scanner(file);
        System.out.println("--- File Contents ---");
        while (fileScanner.hasNextLine()) {
            System.out.println(fileScanner.nextLine());
        }
        fileScanner.close();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== File Reader with Exception Handling ===");
        System.out.print("Enter the file path to read: ");
        String path = scanner.nextLine().trim();

        try {
            if (path.isEmpty()) {
                throw new IllegalArgumentException("No file path provided.");
            }
            readFile(path);
            System.out.println("--- End of File ---");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Please verify the file path and try again.");
        } catch (IOException e) {
            System.out.println("IO Error: Unable to read file. " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("File reading attempt completed.");
        }
    }
}