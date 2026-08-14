import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryManagementSystem {
    // Static nested Book class
    static class Book {
        String title;
        String author;

        Book(String title, String author) {
            this.title = title;
            this.author = author;
        }

        public String toString() {
            return \"Title: \" + title + \", Author: \" + author;
        }
    }

    // Map to store books categorized by genre
    static Map<String, List<Book>> library = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(\"\n--- Library Management System ---\");
            System.out.println(\"1. Add Book\");
            System.out.println(\"2. Display Books by Genre\");
            System.out.println(\"3. Search by Title\");
            System.out.println(\"4. Exit\");
            System.out.print(\"Enter your choice: \");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    displayBooksByGenre();
                    break;
                case 3:
                    searchByTitle();
                    break;
                case 4:
                    System.out.println(\"Exiting...\");
                    return;
                default:
                    System.out.println(\"Invalid choice!\");
            }
        }
    }

    static void addBook() {
        System.out.print(\"Enter Genre: \");
        String genre = scanner.nextLine();
        System.out.print(\"Enter Book Title: \");
        String title = scanner.nextLine();
        System.out.print(\"Enter Author: \");
        String author = scanner.nextLine();

        // computeIfAbsent creates a new ArrayList if genre does not exist
        library.computeIfAbsent(genre, k -> new ArrayList<>()).add(new Book(title, author));
        System.out.println(\"Book added successfully!\");
    }

    static void displayBooksByGenre() {
        if (library.isEmpty()) {
            System.out.println(\"No books in library.\");
            return;
        }
        for (Map.Entry<String, List<Book>> entry : library.entrySet()) {
            System.out.println(\"\nGenre: \" + entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println(\"  \" + book);
            }
        }
    }

    static void searchByTitle() {
        System.out.print(\"Enter book title to search: \");
        String title = scanner.nextLine();
        boolean found = false;

        for (List<Book> books : library.values()) {
            for (Book book : books) {
                if (book.title.equalsIgnoreCase(title)) {
                    System.out.println(\"Found: \" + book);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println(\"Book not found!\");
        }
    }
}
