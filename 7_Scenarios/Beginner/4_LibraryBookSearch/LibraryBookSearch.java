import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryBookSearch {

    public static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    private List<Book> books;

    public LibraryBookSearch() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> searchByTitle(String title) throws BookNotFoundException {
        List<Book> result = books.stream()
                .filter(b -> b.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new BookNotFoundException("Book with title '" + title + "' not found.");
        }
        return result;
    }

    public List<Book> filterByCategory(String category) {
        return books.stream()
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        LibraryBookSearch library = new LibraryBookSearch();

        library.addBook(new Book("Effective Java", "Priya Sharma", "Programming", 2018));
        library.addBook(new Book("Clean Code", "Arjun Patel", "Programming", 2008));
        library.addBook(new Book("The Pragmatic Programmer", "Vikram Singh", "Programming", 2019));
        library.addBook(new Book("Head First Design Patterns", "Diya Reddy", "Programming", 2004));
        library.addBook(new Book("Design Patterns", "Delhi Design Team", "Programming", 1994));
        library.addBook(new Book("Malgudi Days", "R.K. Narayan", "Fantasy", 1937));
        library.addBook(new Book("Swami and Friends", "Chetan Bhagat", "Fiction", 1949));
        library.addBook(new Book("Clean Architecture", "Arjun Patel", "Programming", 2017));
        library.addBook(new Book("Godaan", "Premchand", "Science Fiction", 1965));
        library.addBook(new Book("Java Concurrency in Practice", "Aditya Iyer", "Programming", 2006));

        System.out.println("=== Search by Title ===");
        try {
            List<Book> results = library.searchByTitle("Clean Code");
            results.forEach(System.out::println);
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Filter by Category: Programming ===");
        List<Book> programmingBooks = library.filterByCategory("Programming");
        if (programmingBooks.isEmpty()) {
            System.out.println("No books found in category 'Programming'.");
        } else {
            programmingBooks.forEach(System.out::println);
        }

        System.out.println("\n=== Search by Title (not found) ===");
        try {
            library.searchByTitle("The Art of War");
        } catch (BookNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}



